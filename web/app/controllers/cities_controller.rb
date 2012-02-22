class CitiesController < ApplicationController

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
    
    ret = @cities.collect do |c|
      {
        :id => c.id,
        :name => c.name,
        :yomi => c.yomi,
        :pref => {
          :id => c.prefecture.id,
          :name => c.prefecture.name
        }
      }
    end.to_json
    render :json => ret
  end
  
  def show 
    @prefecture = Prefecture.find(params[:id], :include => :prefecture)
    ret = @cities.collect do |c|
      {
        :id => c.id,
        :name => c.name,
        :yomi => c.yomi,
        :pref => {
          :id => c.prefecture.id,
          :name => c.prefecture.name
        }
      }
    end.to_json
    render :json => ret
  end
  
end
