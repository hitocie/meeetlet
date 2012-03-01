class PrefecturesController < ApiController

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
