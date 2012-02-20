class ApplicationController < ActionController::Base

  # constants
  FB_APP_ID = "150258521734656"
  FB_SITE_PAGE = "http://localhost:3000/api/v1/users" # FIXME:
  FB_APP_SECRET = "c9a8e62a8cbd1b3c0b82f498fd2d4882"

  GNAVI_APP_ID = "5bbd8a15a23d4efb011e8f45fa75ee84"
  GNAVI_SITE_PAGE = "http://api.gnavi.co.jp/ver1/"

  protect_from_forgery

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
  
  
  # common apis
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
end