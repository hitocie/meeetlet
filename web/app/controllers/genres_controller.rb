class GenresController < ApplicationController
  
  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token

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
