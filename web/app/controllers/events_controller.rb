class EventsController < ApplicationController
  
  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token
  
  # GET /events
  def index
    
    case params[:service]
    when "all-events"
      @events = Event.all
    
    when "my-events"
      @events = Event.where(:user_id => session[:user][:id], :canceled => false).all
    
    when "find-events"
      q = "%#{params[:keyword]}%"
      @events = Event.where("user_id = ? AND canceled = ? AND title LIKE ?", session[:user][:id], false, q).all
      
    end
    
    render :json => @events, :include => [:user, :preEvent, :participants]
  end

  # GET /events/1
  def show
    @event = Event.find(params[:id])
    render :json => @event
  end

  # GET /events/new
  def new
    @event = Event.new
    render :json => @event
  end

  # GET /events/1/edit
  def edit
    @event = Event.find(params[:id])
  end

  # POST /events
  def create
    e = params[:event]
    uid = session[:user][:uid]    
    
    case params[:service]
    when "create-event"
      Event.transaction do
        @event = Event.new(
          :title => e[:title], 
          :date => e[:date],
          :place => e[:place],
          :budget => e[:budget], 
          :genre => e[:genre],
          :shop => e[:shop],
          :comment => e[:comment],
          :canceled => false,
          :user_id => session[:user][:id] # owner
        )
        @event.save!
        users = User.where(:uid => e[:participants])
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
          :genres => e[:genres],
          :shops => e[:shops]
        )
        pre_event.save!
        @event = Event.new(
          :title => e[:title], 
          :comment => e[:comment],
          :canceled => false,
          :user_id => session[:user][:id],
          :preEvent_id => pre_event.id)
        @event.save!
        users = User.where(:uid => e[:participants])
        for u in users do
          participant = @event.participants.build(:user_id => u.id)
          participant.save!
        end
      end
    end

    # FIXME: unneed the following @event.save
    if @event.save
      render :json => @event, :status => :created, :location => @event
    else
      render :json => @event.errors, :status => :unprocessable_entity
    end
  end

  # PUT /events/1
  def update
    @event = Event.find(params[:id])
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
      Event.transaction do
        for uid in params[:friends] do
          # TODO: if it has already had the same uid, it should return error.
          @event.participants.build(:user_id => session[:user][:id])
          # TODO: send private message to Facebook.
        end
      end

    end

    if @event.update_attributes(params[:event])
      head :ok
    else
      render :json => @event.errors, :status => :unprocessable_entity
    end
  end

  # DELETE /events/1
  def destroy
    
    @event = Event.find(params[:id])
    user_id = session[:user][:id]
    if @event.user_id != user_id then
      # TODO: error (Not own event.)  
    end
    
    case params[:service]
    when "cancel-event"
      @event.canceled = true
      @event.save!
    
    when "delete-event"
      @event.destroy
    end

    # @event = Event.find(params[:id])
    # @event.destroy

    head :ok
  end
end
