CREATE TABLE administrator
(
    id         VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  TEXT         NOT NULL,
    email      TEXT         NOT NULL,
    password   TEXT         NOT NULL,
    CONSTRAINT pk_administrator PRIMARY KEY (id)
);

ALTER TABLE administrator
    ADD CONSTRAINT uc_administrator_email UNIQUE (email);