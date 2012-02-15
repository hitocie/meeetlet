class CreatePrefectures < ActiveRecord::Migration
  def change
    create_table :prefectures do |t|
      t.string :code
      t.string :name
      t.string :yomi

      t.timestamps
    end
  end
end
