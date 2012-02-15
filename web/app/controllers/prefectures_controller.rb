class PrefecturesController < ApplicationController

  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token

  # GET /prefectures
  def index
    @prefectures = Prefecture.all
    render :json => @prefectures, :except => [:created_at, :updated_at]
  end
end
