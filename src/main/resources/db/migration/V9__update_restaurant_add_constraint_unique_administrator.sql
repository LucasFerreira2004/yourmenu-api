ALTER TABLE restaurant
    ADD CONSTRAINT uc_administrator_restaurant UNIQUE (administrator_id);