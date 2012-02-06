class CreateStations < ActiveRecord::Migration
  def change
    create_table :stations do |t|
      t.string :name
      t.string :yomi
      t.string :line
      t.string :lat
      t.string :lng
      t.references :prefecture

      t.timestamps
    end
    add_index :stations, :prefecture_id
  end
end
