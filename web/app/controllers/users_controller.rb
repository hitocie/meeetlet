class UsersController < ApplicationController

  skip_before_filter :authenticate, :only => [:index]

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
    
    redirect_to MAIN_PAGE
  end

  # GET /users/1
  def show
    service = params[:service]
    case service     
    when "get-me"
      @user = session[:user]
      render :json => {:id => @user.id, :uid => @user.uid, :name => @user.name}.to_json 
      return
      
    when "get-my-friends"
      @user = session[:user]
      graph = Koala::Facebook::API.new(@user.token)
      friends = graph.get_connections("me", "friends")
      ret = friends.collect do |f|
        {
          :uid => f["id"],
          :name => f["name"]
        }
      end.to_json
      render :json => ret
      return

    end
    
    raise "No Service #{service}" 
  end
end
