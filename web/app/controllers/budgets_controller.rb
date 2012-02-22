class BudgetsController < ApplicationController

  def index
    @budgets = Budget.order(:id).all
    ret = @budgets.collect do |b|
      {
        :id => b.id,
        :price => b.price
      }
    end.to_json
    render :json => ret
  end
  
end
