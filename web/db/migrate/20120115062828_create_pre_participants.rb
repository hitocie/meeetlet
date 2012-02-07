class CreatePreParticipants < ActiveRecord::Migration
  def change
    create_table :pre_participants do |t|
      t.text :dates
      t.text :places
      t.text :stations
      t.text :budgets
      t.text :genres
      t.text :shops
      t.string :comment

      t.timestamps
    end
  end
end
