class PreParticipant < ActiveRecord::Base
  serialize :dates, Array
  serialize :cities, Array
  serialize :stations, Array
  serialize :budgets, Array
  serialize :genres, Array
  serialize :shops, Array
end
