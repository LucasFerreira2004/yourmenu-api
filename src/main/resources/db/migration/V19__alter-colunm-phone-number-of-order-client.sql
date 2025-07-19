ALTER TABLE order_client
    ALTER COLUMN phone TYPE VARCHAR(11)
    USING phone::VARCHAR;

