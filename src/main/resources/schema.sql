CREATE TABLE student
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `first_name`    VARCHAR(128) NOT NULL,
    `last_name`     VARCHAR(128) NOT NULL,
    `date_of_birth` DATE         NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE address
(
    `id`           BIGINT       NOT NULL AUTO_INCREMENT,
    `address_type` VARCHAR(32)  NOT NULL,
    `street`       VARCHAR(128) NOT NULL,
    `number`       VARCHAR(16)  NOT NULL,
    `box`          VARCHAR(16)  NOT NULL,
    `zip`          VARCHAR(8)   NOT NULL,
    `city`         VARCHAR(128) NOT NULL,
    `student_id`   BIGINT       NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT fk__address__student FOREIGN KEY (`student_id`) REFERENCES student (`id`)
);