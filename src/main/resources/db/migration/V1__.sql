CREATE TABLE payment
(
    id           BIGINT NOT NULL,
    created_at   datetime NULL,
    updated_at   datetime NULL,
    ref_id       VARCHAR(255) NULL,
    amount       BIGINT NOT NULL,
    payment_date datetime NULL,
    CONSTRAINT pk_payment PRIMARY KEY (id)
);