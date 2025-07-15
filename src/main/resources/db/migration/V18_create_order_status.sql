CREATE TYPE order_status AS ENUM (
    'PENDING',
    'CONFIRMED',
    'IN_PREPARATION',
    'IN_DELIVERY',
    'DELIVERED',
    'CANCELLED'
);

ALTER TABLE orders
ALTER COLUMN status TYPE order_status
USING status::order_status;