class CitiesController < ApplicationController

  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token

  def index
    @cities = nil;
    if params[:prefecture_id] != nil then
      @cities = City.where(:prefecture_id => params[:prefecture_id]).all
    elsif params[:name] then
      q = "%#{params[:name]}%"
      @cities = City.where("name like ? or yomi like ?", q, q).all
    else
      raise "Not keyword error." # FIXME
    end
    
    render :json => @cities, :except => [:created_at, :updated_at]
  end
  
  def show 
    @prefecture = Prefecture.find(params[:id])
    render :json => @prefecture.cities, :except => [:created_at, :updated_at]
  end
  
end
