class CreateEvents < ActiveRecord::Migration
  def change
    create_table :events do |t|
      t.string :title, :null => false
      t.datetime :date
      t.references :city
      t.references :station
      t.references :budget
      t.references :genre
      t.string :shop
      t.string :comment
      t.integer :maxNumber
      t.datetime :deadline
      t.boolean :closed, :default => false
      t.boolean :canceled, :default => false
      t.boolean :privateOnly, :default => true
      t.references :user, :null => false
      t.references :preEvent

      t.timestamps
    end
    add_index :events, :city_id
    add_index :events, :station_id
    add_index :events, :budget_id
    add_index :events, :genre_id
    add_index :events, :user_id
    add_index :events, :preEvent_id
  end
end
