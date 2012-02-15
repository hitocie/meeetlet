class CreateTrains < ActiveRecord::Migration
  def change
    create_table :trains do |t|
      t.string :name
      t.references :prefecture

      t.timestamps
    end
    add_index :trains, :prefecture_id
  end
end
