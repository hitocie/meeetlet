class Participant < ActiveRecord::Base
  belongs_to :user
  belongs_to :event
  belongs_to :preParticipant, :dependent => :destroy
end
