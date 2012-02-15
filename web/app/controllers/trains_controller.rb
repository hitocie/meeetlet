class TrainsController < ApplicationController
  
  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token

  def index
    @trains = Train.where(:prefecture_id => params[:prefecture_id]).all
    render :json => @trains, :except => [:created_at, :updated_at]
  end
  
end
