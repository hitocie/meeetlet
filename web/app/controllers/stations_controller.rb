class StationsController < ApplicationController

  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token

  def index
    @staions = nil;
    if params[:prefecture_id] != nil then
      @stations = Station.where(:prefecture_id => params[:prefecture_id]).order(:name).all
    elsif params[:name] then
      q = "%#{params[:name]}%"
      @stations = Station.where("name like ?", q).order(:name).all
    else
      # TODO: error
      @stations = Station.all
    end

    render :json => @stations
  end

  def show 
    @prefecture = Prefecture.find(params[:id])
    render :json => @prefecture.stations
  end
  
  # def create
    # @prefecture = Prefecture.find(params[:prefecture_id])
    # @station = @prefecture.stations.create(params[:station])
    # redirect_to prefecture_path(@prefecture)
  # end
  
  # def destroy
    # @prefecture = Prefecture.find(params[:prefecture_id])
    # @station = @prefecture.stations.find(params[:id])
    # @station.destroy
    # redirect_to prefecture_path(@prefecture)
  # end
end
