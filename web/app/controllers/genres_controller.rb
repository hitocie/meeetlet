class GenresController < ApiController
  
  def index
    @genres = Genre.order(:id).all
    render :json => to_plain_genres(@genres).to_json
  end
end
