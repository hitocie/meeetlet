class Event < ActiveRecord::Base
  belongs_to :user # owner
  belongs_to :city 
  belongs_to :station
  belongs_to :budget 
  belongs_to :genre
  belongs_to :preEvent, :dependent => :destroy
  
  has_many :participants, :dependent => :destroy
end
