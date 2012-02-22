class GenresController < ApplicationController
  
  def index
    @genres = Genre.order(:id).all
    ret = @genres.collect do |g|
      {
        :id => g.id,
        :name => g.name
      }
    end.to_json
    render :json => ret
  end
end
