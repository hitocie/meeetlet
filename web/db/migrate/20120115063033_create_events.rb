class CreateEvents < ActiveRecord::Migration
  def change
    create_table :events do |t|
      t.string :title
      t.date :date
      t.string :place
      t.string :station
      t.string :budget
      t.string :genre
      t.string :shop
      t.string :comment
      t.integer :maxNumber
      t.boolean :canceled
      t.boolean :privateOnly
      t.references :user
      t.references :preEvent

      t.timestamps
    end
    add_index :events, :user_id
    add_index :events, :preEvent_id
  end
end
