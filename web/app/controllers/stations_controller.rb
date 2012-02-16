class StationsController < ApplicationController

  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token

  def index
    @staions = nil;
    if params[:train_id] != nil then
      @stations = Station.find(:all, :conditions => ["train_id = ?", params[:train_id]], :include => {:train => :prefecture})
    elsif params[:name] then
      q = "%#{params[:name]}%"
      @stations = 
        Station.find(:all, :conditions => ["name like ? or yomi like ?", q, q], :include => {:train => :prefecture})
    else
      raise "Not keyword error." # FIXME
    end

    ret = @stations.collect do |s|
      {
        :id => s.id,
        :name => s.name,
        :yomi => s.yomi,
        :lat => s.lat,
        :lng => s.lng,
        :train => {
          :id => s.train.id,
          :name => s.train.name,
          :pref => {
            :id => s.train.prefecture.id,
            :name => s.train.prefecture.name
          }
        }
      }
    end.to_json
    render :json => ret
  end
  
end
