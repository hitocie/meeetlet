class User < ActiveRecord::Base
  validates :name, :presence => true
  
  def as_json(options = {})
    return {:id => self.id, :uid => self.uid, :name => self.name}
  end
end
