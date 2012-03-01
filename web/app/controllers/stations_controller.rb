class StationsController < ApiController

  def index
    @staions = nil;
    case params[:service]
    when "get-stations"
      @stations = Station.find(:all, 
                               :conditions => ["train_id = ?", params[:train_id]], 
                               :include => {:train => :prefecture}
                              )
    
    when "find-stations"
      query = "%#{params[:name]}%"
      @stations = 
        Station.find(:all, 
                     :conditions => ["name like ? or yomi like ?", query, query], 
                     :include => {:train => :prefecture}
                    )
      
    end
    if @stations == nil then
      raise "Cannot find Station objects." 
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
