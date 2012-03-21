class ApiController < ActionController::Base

  # constants
  BASE_URL = "http://localhost:3000"
  MAIN_PAGE = "#{BASE_URL}/main"

  FB_SITE_PAGE = "#{BASE_URL}/api/v1/users"
  FB_APP_ID = "150258521734656"
  FB_APP_SECRET = "c9a8e62a8cbd1b3c0b82f498fd2d4882"

  GNAVI_APP_ID = "5bbd8a15a23d4efb011e8f45fa75ee84"
  GNAVI_SITE_PAGE = "http://api.gnavi.co.jp/ver1/"

  protect_from_forgery
  
  # NOTE: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token

  # authenticate check  
  before_filter :authenticate
  def authenticate
    if not session[:user] then
      raise "Security Error"
    end
  end

  # error handling
  rescue_from RuntimeError, :with => :handle_server_error
  
  def handle_server_error(exception = nil)
    e = {:failed => true}
    if exception then
      e[:message] = exception.message
    else
      e[:message] = "unknown error"
    end
    
    logger.info "---- ERROR ---- #{e[:message]}"
    render :json => e, :status => 404
  end
  
  
  ### common apis ###
  
  # Facebook apis
  def put_to_fb_wall(message)
    # TODO: Should remove
    return

    @user = session[:user]
    graph = Koala::Facebook::API.new(@user.token)
    graph.put_wall_post(message)
  end
  def put_to_fb_event(name, desc, start_time)
    # TODO: Should remove
    return
    
    @user = session[:user]
    graph = Koala::Facebook::API.new(@user.token)
    # TODO
    #image_path = "XXXXXXX"
    #picture = Koala::UploadableIO.new(File.open(image_path))
    params = {
      #:picture => picture,
      :name => name,
      :description => desc,
      :start_time => start_time
    }
    graph.put_object('me', 'events', params)
  end
  
  
  def date_to_string(d)
    return (d != nil ? d.strftime("%Y-%m-%d %H:%M:%S") : nil)
  end
  
  def create_user_if_not_exists(uid, name, token)
    user = User.where(:uid => uid).first
    if not user then
      user = User.new(:uid => uid, :name => name, :token => token)
    elsif token then
      user.update_attributes(:token => token)
    end
    user.save
    return user
  end

  # format
  def to_plain_cities(cities)
    cities.collect do |c|
      {
        :id => c.id,
        :name => c.name,
        :yomi => c.yomi,
        :pref => {
          :id => c.prefecture.id,
          :name => c.prefecture.name
        }
      }
    end
  end
  def to_plain_budgets(budgets)
    budgets.collect do |b|
      {
        :id => b.id,
        :price => b.price
      }
    end
  end
  def to_plain_genres(genres)
    genres.collect do |g|
      {
        :id => g.id,
        :name => g.name
      }
    end
  end
  def to_plain_stations(stations)
    stations.collect do |s|
      {
        :id => s.id,
        :name => s.name,
        :yomi => s.yomi,
        :lat => s.lat,
        :lng => s.lng,
        :train => {
          :id => s.train.id,
          :name => s.train.name,
          :pref => {
            :id => s.train.prefecture.id,
            :name => s.train.prefecture.name
          }
        }
      }
    end
  end
end
