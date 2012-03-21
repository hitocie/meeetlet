class CitiesController < ApiController

  def index
    @cities = nil;
    case params[:service]
    when "get-cities"
      @cities = City.find(:all, 
                          :conditions => ["prefecture_id = ?", params[:prefecture_id]], 
                          :include => :prefecture
                         )
                         
    when "find-cities"
      query = "%#{params[:name]}%"
      pref_id = params[:prefecture_id].to_i
      conditions = ["(name like ? OR yomi like ?)", query, query]
      if pref_id > 0 then
        conditions[0] << " AND prefecture_id = ?"
        conditions << pref_id
      end
      @cities = City.find(:all, 
                          :conditions => conditions, 
                          :include => :prefecture
                         )
            
    end
    if @cities == nil then
      raise "Not find City objects."
    end
    
    render :json => to_plain_cities(@cities).to_json
  end
  
end
