class BudgetsController < ApplicationController

  # FIXME: The follows is workaround to use sessions. (CSRF token authenticity)
  skip_before_filter :verify_authenticity_token

  def index
    @budgets = Budget.order(:id).all
    render :json => @budgets, :except => [:created_at, :updated_at]
  end
  
end
