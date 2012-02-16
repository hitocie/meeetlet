class PrefecturesController < ApplicationController

  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token

  # GET /prefectures
  def index
    @prefectures = Prefecture.find(:all)
    ret = @prefectures.collect do |p|
      {
        :id => p.id,
        :name => p.name,
        :yomi => p.yomi
      }
    end.to_json
    render :json => ret
  end
end
