class EventsController < ApplicationController
  
  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token
  
  def participants_as_object(participants)
    participants.collect do |p|
      {
        :id => p.id,
        :attend => p.attend,
        :comment => p.comment
      }
    end
  end
  
  def event_as_object(e)
      {
        :id => e.id, 
        :title => e.title,
        :date => e.date,
        :place => e.place,
        :station => e.station,
        :budget => e.budget,
        :genre => e.genre,
        :shop => e.shop,
        :comment => e.comment,
        :maxNumber => e.maxNumber,
        :deadline => e.deadline,
        :closed => e.closed,
        :canceled => e.canceled,
        :privateOnly => e.privateOnly,
        :user => {
          :id => e.user.id,
          :uid => e.user.uid,
          :name => e.user.name
        },
        :preEvent => {
          :id => e.preEvent.id,
          :dates => e.preEvent.dates,
          :places => e.preEvent.places,
          :stations => e.preEvent.stations,
          :budgets => e.preEvent.budgets,
          :genres => e.preEvent.genres,
          :shops => e.preEvent.shops
        },
        :participants => participants_as_object(e.participants)
      }
  end
  
  def events_as_object(events)
    events.collect do |e|
      event_as_object(e)
    end
  end
  
  
  # GET /events
  def index
    
    case params[:service]
    when "all-events"
      @events = Event.find(:all, :order => :date, :include => [:user, :preEvent, :participants])
    
    when "my-events"
      @events = Event.find(:all, 
                           :conditions => ["user_id = ? and canceled =?", session[:user][:id], false],
                           :order => :date,
                           :include => [:user, :preEvent, :participants])
    
    when "find-events"
      q = "%#{params[:keyword]}%"
      @events = Event.find(:all,
                           :conditions => ["user_id = ? AND canceled = ? AND title LIKE ?", session[:user][:id], false, q], 
                           :order => :date,
                           :include => [:user, :preEvent, :participants])
      
    end
    
    render :json => events_as_object(@events).to_json
  end

  # GET /events/1
  def show
    @event = Event.find(params[:id])
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
      participants << p[:id]
      create_user_if_not_exists(p[:id], p[:name], nil)
    end
    
    case params[:service]
    when "create-event"
      Event.transaction do
        @event = Event.new(
          :title => e[:title], 
          :date => e[:date],
          :place => e[:place],
          :station => e[:station],
          :budget => e[:budget], 
          :genre => e[:genre],
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
        for u in users do
          participant = @event.participants.build(:user_id => u.id)
          participant.save!
        end
      end
      
    when "create-pre-event"
      Event.transaction do
        pre_event = PreEvent.new(
          :dates => e[:dates],
          :places => e[:budgets],
          :stations => e[:stations],
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
        for u in users do
          participant = @event.participants.build(:user_id => u.id)
          participant.save!
        end
      end
      
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
          user = create_user_if_not_exists(f[:id], f[:name], nil)
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
    
    @event = Event.find(params[:id], :include => [:user, :preEvent, :participants])
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
