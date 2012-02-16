class BudgetsController < ApplicationController

  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token

  def index
    @budgets = Budget.order(:id).all
    ret = @budgets.collect do |b|
      {
        :id => b.id,
        :id => b.price
      }
    end.to_json
    render :json => ret
  end
  
end
