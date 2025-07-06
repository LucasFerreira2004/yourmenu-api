CREATE SEQUENCE IF NOT EXISTS delivery_zone_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE delivery_zone
(
    id            BIGINT       NOT NULL,
    zone          VARCHAR(255) NOT NULL,
    delivery_fee  DECIMAL      NOT NULL,
    restaurant_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_delivery_zone PRIMARY KEY (id)
);

ALTER TABLE delivery_zone
    ADD CONSTRAINT FK_DELIVERY_ZONE_ON_RESTAURANT FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);