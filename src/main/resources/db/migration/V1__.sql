CREATE TABLE category
(
    id         BIGINT NOT NULL AUTO_INCREMENT,
    created_at datetime NULL,
    updated_at datetime NULL,
    name       VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT NOT NULL AUTO_INCREMENT,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    name          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    price         INT    NOT NULL,
    category_id   BIGINT NULL,
    imageurl      VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);