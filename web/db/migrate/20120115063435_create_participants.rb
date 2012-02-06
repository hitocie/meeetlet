class CreateParticipants < ActiveRecord::Migration
  def change
    create_table :participants do |t|
      t.integer :attend
      t.string :comment
      t.references :event
      t.references :user
      t.references :preParticipant

      t.timestamps
    end
    add_index :participants, :event_id
    add_index :participants, :user_id
    add_index :participants, :preParticipant_id
  end
end
