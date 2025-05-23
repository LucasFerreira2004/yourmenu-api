CREATE TABLE restaurant
(
    id                VARCHAR(255) NOT NULL,
    administrator_id  VARCHAR(255) NOT NULL,
    slug              VARCHAR(255) NOT NULL,
    name              VARCHAR(255) NOT NULL,
    delivery_time_min INTEGER,
    delivery_time_max INTEGER,
    is_open           BOOLEAN,
    profile_pic_url   TEXT,
    banner_pic_url    TEXT,
    CONSTRAINT pk_restaurant PRIMARY KEY (id)
);

ALTER TABLE restaurant
    ADD CONSTRAINT uc_restaurant_slug UNIQUE (slug);

ALTER TABLE restaurant
    ADD CONSTRAINT FK_RESTAURANT_ON_ADMINISTRATOR FOREIGN KEY (administrator_id) REFERENCES administrator (id);