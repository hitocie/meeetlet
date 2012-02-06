class CreatePreEvents < ActiveRecord::Migration
  def change
    create_table :pre_events do |t|
      t.text :dates
      t.text :places
      t.text :budgets
      t.text :genres
      t.text :shops

      t.timestamps
    end
  end
end
