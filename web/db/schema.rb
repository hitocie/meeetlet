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

ActiveRecord::Schema.define(:version => 20120130154935) do

  create_table "cities", :force => true do |t|
    t.string   "name"
    t.string   "yomi"
    t.integer  "prefecture_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "cities", ["prefecture_id"], :name => "index_cities_on_prefecture_id"

  create_table "events", :force => true do |t|
    t.string   "title"
    t.date     "date"
    t.string   "place"
    t.string   "budget"
    t.string   "genre"
    t.string   "shop"
    t.string   "comment"
    t.boolean  "canceled"
    t.integer  "user_id"
    t.integer  "preEvent_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "events", ["preEvent_id"], :name => "index_events_on_preEvent_id"
  add_index "events", ["user_id"], :name => "index_events_on_user_id"

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
    t.text     "places"
    t.text     "budgets"
    t.text     "genres"
    t.text     "shops"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "pre_participants", :force => true do |t|
    t.text     "dates"
    t.text     "places"
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
    t.string   "line"
    t.string   "lat"
    t.string   "lng"
    t.integer  "prefecture_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "stations", ["prefecture_id"], :name => "index_stations_on_prefecture_id"

  create_table "users", :force => true do |t|
    t.string   "uid"
    t.string   "name"
    t.string   "token"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

end
