class Station < ActiveRecord::Base
  validates :name, :presence => true

#  belongs_to :prefecture
  belongs_to :train
end
