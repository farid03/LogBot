CREATE TABLE log_entries(
    id SERIAL PRIMARY KEY,
    message TEXT,
    received_at TIMESTAMP WITH TIME ZONE
);