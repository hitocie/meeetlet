class CitiesController < ApplicationController

  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token

  def index
    @cities = nil;
    if params[:prefecture_id] != nil then
      @cities = City.find(:all, :conditions => ["prefecture_id = ?", params[:prefecture_id]], :include => :prefecture)
    elsif params[:name] then
      q = "%#{params[:name]}%"
      @cities = City.find(:all, :conditions => ["name like ? or yomi like ?", q, q], :include => :prefecture)
    else
      raise "Not keyword error." # FIXME
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
