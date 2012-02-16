class CreateStations < ActiveRecord::Migration
  def change
    create_table :stations do |t|
      t.string :name
      t.string :yomi
      t.float :lat
      t.float :lng
      t.references :train

      t.timestamps
    end
    add_index :stations, :train_id 
  end
end
