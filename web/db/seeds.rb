# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ :name => 'Chicago' }, { :name => 'Copenhagen' }])
#   Mayor.create(:name => 'Emanuel', :city => cities.first)

# delete all
Budget.delete_all
City.delete_all
Station.delete_all
Train.delete_all
Prefecture.delete_all


# prefectures
prefs = ActiveSupport::JSON.decode(File.read("#{Rails.root}/db/resources/prefs.json"))
for p in prefs do
  pref = Prefecture.new(:code => sprintf("%0#{2}d", p["order"]), :name => p["name"], :yomi => p["yomi"])
  pref.save!
end

# cities
cities = ActiveSupport::JSON.decode(File.read("#{Rails.root}/db/resources/cities.json"))
for c in cities do
  p = Prefecture.where(:name => c["pref"]).first
  city = City.new(:name => c["name"], :yomi => c["yomi"], :prefecture_id => p.id)
  city.save!
end

# stations
stations = ActiveSupport::JSON.decode(File.read("#{Rails.root}/db/resources/stations.json"))
cnt = 0
for s in stations do
  p = Prefecture.where(:name => s["pref"]).first
  train = Train.where(:name => s["line"], :prefecture_id => p.id).first
  if (train == nil) then
    train = Train.new(:name => s["line"], :prefecture_id => p.id)
    train.save!
  end
  station = Station.new(:name => s["name"], 
                        :yomi => s["yomi"], 
                        :train_id => train.id,
                        :lat => s["lat"], 
                        :lng => s["lng"])
  station.save!
end

# budgets
prices = [
  "〜¥999",
  "¥1,000〜¥1,999", 
  "¥2,000〜¥2,999", 
  "¥3,000〜¥3,999", 
  "¥4,000〜¥4,999", 
  "¥5,000〜¥5,999", 
  "¥6,000〜¥6,999",
  "¥7,000〜¥7,999",
  "¥8,000〜¥8,999",
  "¥9,000〜¥9,999",
  "¥10,000〜¥14,999",
  "¥15,000〜¥19,999",
  "¥20,000〜¥24,999",
  "¥25,000〜¥29,999",
  "¥30,000〜"]
for p in prices do
  b = Budget.new(:price => p)
  b.save!
end