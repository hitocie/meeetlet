class CreateStations < ActiveRecord::Migration
  def change
    create_table :stations do |t|
      t.string :name
      t.string :yomi
      t.string :lat
      t.string :lng
#      t.references :prefecture
      t.references :train

      t.timestamps
    end
    add_index :stations, :train_id #, :prefecture_id
  end
end
