class NewsController < ApiController

  def index
    @news = News.order(:date).all
    ret = @news.collect do |n|
      {
        :id => n.id,
        :date => n.date,
        :content => n.content
      }
    end.to_json
    render :json => ret
  end
  
  def create
    n = params[:news]
    News.transaction do
      @news = News.new(:date => n[:date], :content => n[:content])
      @news.save!
    end
    
    render :json => @news, :status => :created, :location => @news
  end
end
