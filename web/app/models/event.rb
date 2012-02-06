class Event < ActiveRecord::Base
  belongs_to :user # owner
  belongs_to :preEvent, :dependent => :destroy
  
  has_many :participants, :dependent => :destroy
end
