ALTER TABLE session
    DROP FOREIGN KEY FK_SESSION_ON_USER;

CREATE TABLE token
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    created_at  datetime              NULL,
    updated_at  datetime              NULL,
    deleted     BIT(1)                NOT NULL,
    value       VARCHAR(255)          NULL,
    user_id     BIGINT                NULL,
    expiring_at datetime              NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

ALTER TABLE `role`
    ADD deleted BIT(1) NOT NULL;


ALTER TABLE user
    ADD deleted BIT(1) NOT NULL;


ALTER TABLE token
    ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

DROP TABLE session;