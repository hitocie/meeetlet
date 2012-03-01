class ApplicationController < ActionController::Base
  
  before_filter :authenticate
  def authenticate
    if not session[:user] then
      logger.warn("Security error. Redirect to Root.")
      redirect_to root_path
    end
  end

end