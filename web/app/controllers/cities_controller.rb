class CitiesController < ApplicationController

  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token

  def index
    @cities = City.where(:prefecture_id => params[:prefecture_id]).order(:name).all
    render :json => @cities
  end
  
  def show 
    @prefecture = Prefecture.find(params[:id])
    render :json => @prefecture.cities
  end
  
  # def create
    # @prefecture = Prefecture.find(params[:prefecture_id])
    # @city = @prefecture.cities.create(params[:city])
    # redirect_to prefecture_path(@prefecture)
  # end

  # def destroy
    # @prefecture = Prefecture.find(params[:prefecture_id])
    # @city = @prefecture.cities.find(params[:id])
    # @city.destroy
    # redirect_to prefecture_path(@prefecture)
  # end
end
