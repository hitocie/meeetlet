class CreateEvents < ActiveRecord::Migration
  def change
    create_table :events do |t|
      t.string :title, :null => false
      t.datetime :date
      t.string :place
      t.string :station
      t.string :budget
      t.string :genre
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
    add_index :events, :user_id
    add_index :events, :preEvent_id
  end
end
