class Prefecture < ActiveRecord::Base
  validates :code, :presence => true, :length => { :minimum => 2}
  validates :name, :presence => true

  has_many :cities, :dependent => :destroy
  has_many :stations, :dependent => :destroy
end
