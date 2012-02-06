class CreateCities < ActiveRecord::Migration
  def change
    create_table :cities do |t|
      t.string :name
      t.string :yomi
      t.references :prefecture

      t.timestamps
    end
    add_index :cities, :prefecture_id
  end
end
