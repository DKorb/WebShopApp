--liquibase formatted SQL
--changeset DKorb:1


CREATE TABLE users
(
    id         INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email      VARCHAR(48)     NOT NULL UNIQUE,
    first_name VARCHAR(24)     NOT NULL,
    last_name  VARCHAR(24)     NOT NULL,
    password   VARCHAR(128)    NOT NULL,
    status     BIT(1)          NOT NULL
);

CREATE TABLE roles
(
    id   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(24)     NOT NULL UNIQUE
);

CREATE TABLE users_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE categories
(
    id     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    alias  VARCHAR(48)     NOT NULL UNIQUE,
    image  VARCHAR(128)    NOT NULL,
    name   VARCHAR(48)     NOT NULL UNIQUE,
    status BIT(1) DEFAULT NULL
);

CREATE TABLE brands
(
    id   INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    logo VARCHAR(128)    NOT NULL,
    name VARCHAR(48)     NOT NULL UNIQUE
);

CREATE TABLE brands_categories
(
    brand_id    INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (brand_id, category_id),
    FOREIGN KEY (brand_id) REFERENCES brands (id),
    FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE products
(
    id               INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name             VARCHAR(64)     NOT NULL UNIQUE,
    alias            VARCHAR(64)     NOT NULL UNIQUE,
    full_description VARCHAR(4096)   NOT NULL,
    created_time     DATETIME,
    updated_time     DATETIME,
    status           BIT(1)          NOT NULL,
    in_stock         BIT(1),
    price            FLOAT           NOT NULL,
    image            VARCHAR(128)    NOT NULL,
    brand_id         INT,
    category_id      INT
);

CREATE TABLE product_details
(
    id         INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name       VARCHAR(64)     NOT NULL,
    value      VARCHAR(64)     NOT NULL,
    product_id INT DEFAULT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE customers
(
    id           INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    address      VARCHAR(64)     NOT NULL,
    city         VARCHAR(64)     NOT NULL,
    created_time DATETIME DEFAULT NULL,
    email        VARCHAR(48)     NOT NULL UNIQUE,
    first_name   VARCHAR(24)     NOT NULL,
    last_name    VARCHAR(24)     NOT NULL,
    password     VARCHAR(128)    NOT NULL,
    phone_number VARCHAR(12)     NOT NULL,
    status       BIT(1)          NOT NULL
);

CREATE TABLE cart_items
(
    id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    quantity    INT             NOT NULL,
    customer_id INT DEFAULT NULL,
    product_id  INT DEFAULT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id),
    FOREIGN KEY (customer_id) REFERENCES customers (id)
)
