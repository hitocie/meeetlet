class PreEvent < ActiveRecord::Base
  serialize :dates, Array
  serialize :places, Array
  serialize :budgets, Array
  serialize :genres, Array
  serialize :shops, Array
end
