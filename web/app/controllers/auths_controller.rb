class AuthsController < ApplicationController

  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :authenticate, :verify_authenticity_token

  def index
    # auth
    url = "https://graph.facebook.com/oauth/authorize?client_id="
    url << FB_APP_ID << "&redirect_uri=" << FB_SITE_PAGE << "&scope=manage_pages,publish_stream,offline_access,manage_pages"
    redirect_to url
  end
  
  def show
    # is-login
    @user = session[:user]
    render :json => (@user ? true : false)
  end
end
