# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ :name => 'Chicago' }, { :name => 'Copenhagen' }])
#   Mayor.create(:name => 'Emanuel', :city => cities.first)

prefs = ["–kŠC“¹", "ÂX"]
count = 1
for pref in prefs do
  p = Prefecture.new(:code => count, :name => pref)
  p.save!
  count += 1
end