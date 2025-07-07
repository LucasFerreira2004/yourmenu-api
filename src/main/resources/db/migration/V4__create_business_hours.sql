CREATE TABLE business_hours
(
    id_business_hours UUID         NOT NULL,
    restaurant_id     VARCHAR(255) NOT NULL,
    weekday           VARCHAR(255),
    opening_time      time WITHOUT TIME ZONE,
    closing_time      time WITHOUT TIME ZONE,
    CONSTRAINT pk_businesshours PRIMARY KEY (id_business_hours)
);

ALTER TABLE business_hours
    ADD CONSTRAINT FK_BUSINESSHOURS_ON_RESTAURANT FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);