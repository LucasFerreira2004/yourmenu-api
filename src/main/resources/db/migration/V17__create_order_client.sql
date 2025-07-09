CREATE SEQUENCE IF NOT EXISTS order_client_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE order_client
(
    id         BIGINT      NOT NULL,
    order_id   BIGINT      NOT NULL,
    first_name TEXT        NOT NULL,
    last_name  TEXT        NOT NULL,
    phone      numeric(11) NOT NULL,
    CONSTRAINT pk_orderclient PRIMARY KEY (id)
);

ALTER TABLE order_client
    ADD CONSTRAINT uc_orderclient_order UNIQUE (order_id);

ALTER TABLE order_client
    ADD CONSTRAINT FK_ORDERCLIENT_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);