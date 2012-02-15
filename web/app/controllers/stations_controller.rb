class StationsController < ApplicationController

  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token

  def index
    @staions = nil;
    if params[:train_id] != nil then
      @stations = Station.where(:train_id => params[:train_id]).all
    elsif params[:name] then
      q = "%#{params[:name]}%"
      @stations = Station.where("name like ? or yomi like ?", q, q).all
    else
      raise "Not keyword error." # FIXME
    end

    render :json => @stations, :except => [:created_at, :updated_at]
  end
  
end
