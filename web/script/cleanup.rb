# encoding: utf-8

# If date is past, set true to close column.
events = Event.find(:all,
                    :conditions => ["close = false AND date < ?", Date.today]
                   )
for e in events do
  e.closed = true
  e.save!
end


# README
# > rails runner script/cleanup.rb
