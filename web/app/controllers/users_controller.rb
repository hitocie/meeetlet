class UsersController < ApplicationController

  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :authenticate, :only => [:index]
  skip_before_filter :verify_authenticity_token
  

  # GET /users
  def index
    validate_code = params[:code]
    logger.info "VALIDATE CODE=#{validate_code}"
    
    u = "https://graph.facebook.com/oauth/access_token"
    client = HTTPClient.new
    access_token = client.get(u, :query => {
      :client_id => FB_APP_ID, 
      :redirect_uri => FB_SITE_PAGE, 
      :client_secret => FB_APP_SECRET, 
      :code => validate_code 
    }).body
    access_token.slice!(0, 13) # delete "access_token"
    logger.info "ACCESS_TOKEN=#{access_token}"
    
    graph = Koala::Facebook::API.new(access_token)
    me = graph.get_object("me")
    @user = create_user_if_not_exists(me["id"], me["name"], access_token)
    reset_session
    session[:user] = @user
    
    # FIXME: Top page
    redirect_to "http://localhost:3000/www/"
  end

  # GET /users/1
  def show
    service = params[:service]
    case service     
    when "get-me"
      @user = session[:user]
      render :json => @user
      return
      
    when "get-my-friends"
      @user = session[:user]
      graph = Koala::Facebook::API.new(@user.token)
      friends = graph.get_connections("me", "friends")
      render :json => friends
      return

    end
    
    raise "No Service #{service}" 
  end

  # GET /users/new
  def new
    @user = User.new
    render :json => @user
  end

  # GET /users/1/edit
  def edit
    @user = User.find(params[:id])
  end

  # POST /users
  def create
    p params[:user][:name] # [:user]
    @user = User.new(params[:user])

    if @user.save
      render :json => @user, :status => :created, :location => @user
    else
      render :json => @user.errors, :status => :unprocessable_entity
    end
  end

  # PUT /users/1
  def update
    @user = User.find(params[:id])

    if @user.update_attributes(params[:user])
      head :ok
    else
      render :json => @user.errors, :status => :unprocessable_entity
    end
  end

  # DELETE /users/1
  def destroy
    @user = User.find(params[:id])
    @user.destroy

    head :ok
  end
end
