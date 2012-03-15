class EventsController < ApiController
  
  def participants_as_object(participants)
    participants.collect do |p|
      obj = {
        :id => p.id,
        :friend => {
          :id => p.user.id,
          :uid => p.user.uid,
          :name => p.user.name
        },
        :attend => p.attend,
        :comment => p.comment
      }
      pp = p.preParticipant
      if pp != nil then
        obj[:preParticipant] = {
          :id => pp.id,
          :dates => pp.dates,
          :cities => pp.cities,
          :stations => pp.stations,
          :budgets => pp.budgets,
          :genres => pp.genres,
          :shops => pp.shops,
          :comment => pp.comment
        }
      end
      obj
    end
  end
  
  def event_as_object(e)
    obj = {
      :id => e.id, 
      :title => e.title,
      :date => date_to_string(e.date),
      :shop => e.shop,
      :comment => e.comment,
      :maxNumber => e.maxNumber,
      :deadline => date_to_string(e.deadline),
      :closed => e.closed,
      :canceled => e.canceled,
      :privateOnly => e.privateOnly,
      :owner => {
        :id => e.user.id,
        :uid => e.user.uid,
        :name => e.user.name
      }
    }
      
    if e.city != nil then
      obj[:city] = {
        :id => e.city.id,
        :name => e.city.name,
        :pref => {
          :id => e.city.prefecture.id,
          :name => e.city.prefecture.name
        }
      }
    end
    if e.station != nil then
      obj[:station] = {
        :id => e.station.id,
        :name => e.station.name,
        :train => {
          :id => e.station.train.id,
          :name => e.station.train.name
        }, 
        :lat => e.station.lat,
        :lng => e.station.lng
      }
    end
    if e.budget != nil then
      obj[:budget] = {
        :id => e.budget.id,
        :price => e.budget.price
      }
    end
    if e.genre != nil then
      obj[:genre] = {
        :id => e.genre.id,
        :name => e.genre.name
      }
    end
    if e.preEvent != nil then
      obj[:preEvent] = {
        :id => e.preEvent.id,
        :dates => e.preEvent.dates,
        :cities => e.preEvent.cities,
        :stations => e.preEvent.stations,
        :budgets => e.preEvent.budgets,
        :genres => e.preEvent.genres,
        :shops => e.preEvent.shops
      }
    end
    if e.participants != nil then
      obj[:participants] = participants_as_object(e.participants)
    end
    return obj
  end
  
  def events_as_object(events)
    events.collect do |e|
      {
        :id => e.id, 
        :title => e.title,
        :date => date_to_string(e.date),
        :comment => e.comment,
        :privateOnly => e.privateOnly,
        :owner => {
          :id => e.user.id,
          :uid => e.user.uid,
          :name => e.user.name
        },
        :preEvent => if e.preEvent != nil then {:id => e.preEvent.id} end
      }
    end
  end
  
  def make_common_conditions(conditions, include_closed, include_history)
    if include_closed == "false" then
      conditions[0] << " AND closed = ?"
      conditions << false
    end
    if include_history == "false" then
      conditions[0] << " AND (date IS NULL OR date > ?)"
      conditions << DateTime.now
    end
  end
  
  
  
  # GET /events
  def index
    
    case params[:service]
    # when "all-events"
      # conditions = ["canceled = ?", false]
      # make_common_conditions(conditions, params[:include_closed], params[:include_history])
      # @events = Event.find(:all, 
                           # :conditions => conditions,
                           # :order => :date, 
                           # :include => [:user, :preEvent]
                          # )
    
    when "public-events"
      conditions = ["privateOnly = ? AND canceled = ?", false, false]
      make_common_conditions(conditions, params[:include_closed], params[:include_history])
      @events = Event.find(:all, 
                           :conditions => conditions,
                           :order => :date,
                           :include => [:user, :preEvent, :participants]
                          )
    
    when "my-events"
      user_id = session[:user][:id]
      conditions =
        ["(events.user_id = ? OR participants.user_id = ?) AND canceled = ?", user_id, user_id, false]
      make_common_conditions(conditions, params[:include_closed], params[:include_history])
      @events = Event.find(:all, 
                           :conditions => conditions,
                           :order => :date,
                           :include => [:user, :preEvent, :participants]
                          )
    
    when "find-events"
      user_id = session[:user][:id]
      query = "%#{params[:keyword]}%"
      conditions =
        ["(events.user_id = ? OR participants.user_id = ?) AND canceled = ? AND title LIKE ?", 
          user_id, user_id, false, query]
      make_common_conditions(conditions, params[:include_closed], params[:include_history])
      @events = Event.find(:all,
                           :conditions => conditions, 
                           :order => :date,
                           :include => [:user, :preEvent, :participants]
                          )
      
    end
    
    render :json => events_as_object(@events).to_json
  end

  # GET /events/1
  def show
    @event = Event.find(:first,
                        :conditions => ["events.id = ?", params[:id].to_i], 
                        :include => [:user,
                                     :preEvent,
                                     {:city => [:prefecture]}, 
                                     {:station => [:train]}, 
                                     :budget, 
                                     :genre, 
                                     {:participants => [:user, :preParticipant]}
                                    ]
                       )
    render :json => event_as_object(@event).to_json
  end

  # GET /events/new
  def new
    @event = Event.new
    render :json => event_as_object(@event).to_json
  end

  # GET /events/1/edit
  def edit
    @event = Event.find(params[:id])
  end

  # POST /events
  def create
    e = params[:event]
    uid = session[:user][:uid]    
    
    # create friends' pre-account if not exists.
    participants = []
    for p in e[:participants] do
      participants << p[:uid]
      create_user_if_not_exists(p[:uid], p[:name], nil)
    end
    
    case params[:service]
    when "create-event"
      Event.transaction do
        @event = Event.new(
          :title => e[:title], 
          :date => e[:date],
          :city_id => e[:city],
          :station_id => e[:station],
          :budget_id => e[:budget], 
          :genre_id => e[:genre],
          :shop => e[:shop],
          :maxNumber => e[:maxNumber],
          :deadline => e[:deadline],
          :comment => e[:comment],
          :closed => false,
          :canceled => false,
          :privateOnly => e[:privateOnly],
          :user_id => session[:user][:id] # owner
        )
        @event.save!
        users = User.where(:uid => participants)
        # own data
        participant = 
          @event.participants.build(
            :user_id => session[:user][:id],
            :attend => true
          )
        participant.save!
        # friends data
        for u in users do
          participant = @event.participants.build(:user_id => u.id)
          participant.save!
        end
        # put event to Facebook. TODO: should add meeetlet url
        put_to_fb_event(@event.title + "\n<here is url.>", @event.comment, @event.date)
      end
      
    when "create-pre-event"
      Event.transaction do
        pre_event = PreEvent.new(
          :dates => e[:dates],
          :cities => e[:cities],
          :stations => e[:stations],
          :budgets => e[:budgets],
          :genres => e[:genres],
          :shops => e[:shops]
        )
        pre_event.save!
        @event = Event.new(
          :title => e[:title],
          :maxNumber => e[:maxNumber], 
          :deadline => e[:deadline],
          :comment => e[:comment],
          :closed => false,
          :canceled => false,
          :privateOnly => e[:privateOnly],
          :user_id => session[:user][:id],
          :preEvent_id => pre_event.id)
        @event.save!
        users = User.where(:uid => participants)
        # own data
        pp = PreParticipant.new(
          :dates => Array.new(e[:dates].size, 0),
          :cities => Array.new(e[:cities].size, 0),
          :stations => Array.new(e[:stations].size, 0),
          :budgets => Array.new(e[:budgets].size, 0),
          :genres => Array.new(e[:genres].size, 0),
          :shops => Array.new(e[:shops].size, 0)
        )
        pp.save!
        participant = 
          @event.participants.build(
            :user_id => session[:user][:id],
            :attend => true,
            :preParticipant_id => pp.id
          )
        participant.save!
        # friends data
        for u in users do
          participant = @event.participants.build(:user_id => u.id)
          participant.save!
        end
      end
      # put message to wall of Facebook.
      put_to_fb_wall(@event.title + "\n<here is url.>") # TODO: should add meeetlet url
    end
    
    render :json => @event, :status => :created, :location => @event
  end

  # PUT /events/1
  def update
    @event = Event.find(params[:id], :include => [:user, :preEvent, :participants])
    # Check if closed event.
    if @event.closed or @event.deadline < Date.today then
      raise "This event has been closed!"
    end
    uid = session[:user][:uid] 

    case params[:service]
    when "reply-pre-event"
      Event.transaction do
        for p in @event.participants do
          if p.user.uid == uid then
            pp = p.preParticipant
            if pp == nil then
              pp = PreParticipant.new(params[:pre_participant])
              pp.save!
              p.preParticipant = pp
              p.save!
            else 
              pp.attributes = params[:pre_participant]
              pp.save!
            end
            break
          end
        end
      end
    
    when "reply-event"
      Event.transaction do
        for p in @event.participants do
          if p.user.uid == uid then
            p.attributes = params[:participant]
            p.save!
            break
          end
        end
      end

    when "invite-event"
      friends = params[:friends]
      max_num = @event.maxNumber
      if max_num != -1 then
        if max_num - @event.participants.size - friends.size < 0 then
          raise "Could not invite due to overcrowded!"
        end
      end
      Event.transaction do
        # create friends' pre-account if not exists.
        ids = []
        for f in friends do
          user = create_user_if_not_exists(f[:uid], f[:name], nil)
          ids << user.id
        end
    
        for id in ids do
          # TODO: if it has already had the same uid, it should return error. or "uniqueness validation"
          @event.participants.build(:user_id => id)
          # TODO: send private message to Facebook.
        end
      end
      @event.update_attributes(params[:event])
      @event.save!
    end

    head :ok
  end

  # DELETE /events/1
  def destroy
    
    @event = Event.find(params[:id])
    user_id = session[:user][:id]
    if @event.user_id != user_id then
      # Not own event.  
      raise "You cannot delete this event!"
    end
    
    case params[:service]
    when "cancel-event"
      @event.canceled = true
      @event.save!
    
    when "delete-event"
      @event.destroy
    end

    head :ok
  end
end
