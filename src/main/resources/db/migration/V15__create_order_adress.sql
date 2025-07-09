CREATE SEQUENCE IF NOT EXISTS order_adress_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE order_adress
(
    id               BIGINT      NOT NULL,
    order_id         BIGINT      NOT NULL,
    delivery_zone_id BIGINT      NOT NULL,
    cep              BIGINT      NOT NULL,
    street           TEXT        NOT NULL,
    number           VARCHAR(20) NOT NULL,
    complement       VARCHAR(100),
    reference        VARCHAR(100),
    CONSTRAINT pk_order_adress PRIMARY KEY (id)
);

ALTER TABLE order_adress
    ADD CONSTRAINT uc_order_adress_order UNIQUE (order_id);

ALTER TABLE order_adress
    ADD CONSTRAINT FK_ORDER_ADRESS_ON_DELIVERY_ZONE FOREIGN KEY (delivery_zone_id) REFERENCES delivery_zone (id);

ALTER TABLE order_adress
    ADD CONSTRAINT FK_ORDER_ADRESS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);