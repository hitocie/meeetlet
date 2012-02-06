# Be sure to restart your server when you modify this file.

#Meeetlet::Application.config.session_store :cookie_store, :key => '_meeetlet_session'
Meeetlet::Application.config.session_store :active_record_store, :key => '_meeetlet_session'

# Use the database for sessions instead of the cookie-based default,
# which shouldn't be used to store highly confidential information
# (create the session table with "rails generate session_migration")
# Meeetlet::Application.config.session_store :active_record_store
