CREATE SEQUENCE seq_event;
CREATE TABLE device (token VARCHAR PRIMARY KEY, registered NUMERIC);
CREATE TABLE event (id NUMERIC PRIMARY KEY, title VARCHAR, event_info VARCHAR, timestamp NUMERIC, event_url VARCHAR);