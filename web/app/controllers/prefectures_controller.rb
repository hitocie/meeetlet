class PrefecturesController < ApplicationController

  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token

  # GET /prefectures
  def index
    @prefectures = Prefecture.all
    render :json => @prefectures
  end

  # GET /prefectures/1
  def show
    @prefecture = Prefecture.find(params[:id])
    render :json => @prefecture
  end

  # def new
    # @prefecture = Prefecture.new
    # render :json => @prefecture
  # end

  # def edit
    # @prefecture = Prefecture.find(params[:id])
  # end

  # def create
    # @prefecture = Prefecture.new(params[:prefecture])
    # if @prefecture.save
      # render :json => @prefecture, :status => :created, :location => @prefecture
    # else
      # render :json => @prefecture.errors, :status => :unprocessable_entity
    # end
  # end

  # def update
    # @prefecture = Prefecture.find(params[:id])
    # if @prefecture.update_attributes(params[:prefecture])
      # head :ok
    # else
      # render :json => @prefecture.errors, :status => :unprocessable_entity
    # end
  # end

  # def destroy
    # @prefecture = Prefecture.find(params[:id])
    # @prefecture.destroy
    # head :ok
  # end
end
