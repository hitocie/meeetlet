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

    render :json => to_plain_stations(@stations).to_json
  end
  
end
