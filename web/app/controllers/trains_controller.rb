class TrainsController < ApiController
  
  def index
    @trains = Train.find(:all, :conditions => ["prefecture_id = ?", params[:prefecture_id]], :include => :prefecture)
    ret = @trains.collect do |t|
     {
       :id => t.id,
       :name => t.name,
       :pref => {
                  :id => t.prefecture.id,
                  :name => t.prefecture.name,
                  :yomi => t.prefecture.yomi
                }
     }
    end.to_json
    render :json => ret 
  end
end
