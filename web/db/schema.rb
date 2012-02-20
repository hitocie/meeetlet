# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended to check this file into your version control system.

ActiveRecord::Schema.define(:version => 20120218054900) do

  create_table "budgets", :force => true do |t|
    t.string   "price"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "cities", :force => true do |t|
    t.string   "name"
    t.string   "yomi"
    t.integer  "prefecture_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "cities", ["prefecture_id"], :name => "index_cities_on_prefecture_id"

  create_table "events", :force => true do |t|
    t.string   "title",                          :null => false
    t.datetime "date"
    t.integer  "city_id"
    t.integer  "station_id"
    t.integer  "budget_id"
    t.integer  "genre_id"
    t.string   "shop"
    t.string   "comment"
    t.integer  "maxNumber"
    t.datetime "deadline"
    t.boolean  "closed",      :default => false
    t.boolean  "canceled",    :default => false
    t.boolean  "privateOnly", :default => true
    t.integer  "user_id",                        :null => false
    t.integer  "preEvent_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "events", ["budget_id"], :name => "index_events_on_budget_id"
  add_index "events", ["city_id"], :name => "index_events_on_city_id"
  add_index "events", ["genre_id"], :name => "index_events_on_genre_id"
  add_index "events", ["preEvent_id"], :name => "index_events_on_preEvent_id"
  add_index "events", ["station_id"], :name => "index_events_on_station_id"
  add_index "events", ["user_id"], :name => "index_events_on_user_id"

  create_table "genres", :force => true do |t|
    t.string   "name"
    t.string   "gcode"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "participants", :force => true do |t|
    t.integer  "attend"
    t.string   "comment"
    t.integer  "event_id"
    t.integer  "user_id"
    t.integer  "preParticipant_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "participants", ["event_id"], :name => "index_participants_on_event_id"
  add_index "participants", ["preParticipant_id"], :name => "index_participants_on_preParticipant_id"
  add_index "participants", ["user_id"], :name => "index_participants_on_user_id"

  create_table "pre_events", :force => true do |t|
    t.text     "dates"
    t.text     "cities"
    t.text     "stations"
    t.text     "budgets"
    t.text     "genres"
    t.text     "shops"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "pre_participants", :force => true do |t|
    t.text     "dates"
    t.text     "cities"
    t.text     "stations"
    t.text     "budgets"
    t.text     "genres"
    t.text     "shops"
    t.string   "comment"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "prefectures", :force => true do |t|
    t.string   "code"
    t.string   "name"
    t.string   "yomi"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "sessions", :force => true do |t|
    t.string   "session_id", :null => false
    t.text     "data"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "sessions", ["session_id"], :name => "index_sessions_on_session_id"
  add_index "sessions", ["updated_at"], :name => "index_sessions_on_updated_at"

  create_table "stations", :force => true do |t|
    t.string   "name"
    t.string   "yomi"
    t.float    "lat"
    t.float    "lng"
    t.integer  "train_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "stations", ["train_id"], :name => "index_stations_on_train_id"

  create_table "trains", :force => true do |t|
    t.string   "name"
    t.integer  "prefecture_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "trains", ["prefecture_id"], :name => "index_trains_on_prefecture_id"

  create_table "users", :force => true do |t|
    t.string   "uid"
    t.string   "name"
    t.string   "token"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

end
