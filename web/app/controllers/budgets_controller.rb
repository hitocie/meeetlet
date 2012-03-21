class BudgetsController < ApiController

  def index
    @budgets = Budget.order(:id).all
    render :json => to_plain_budgets(@budgets).to_json
  end
end
