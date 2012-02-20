class CreateGenres < ActiveRecord::Migration
  def change
    create_table :genres do |t|
      t.string :name
      t.string :gcode # gnavi code
      t.timestamps
    end
  end
end
