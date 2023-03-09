CREATE TABLE IF NOT EXISTS products
(
    id    BIGINT NOT NULL PRIMARY KEY,
    title VARCHAR(255),
    price INT
);

CREATE TABLE IF NOT EXISTS orders
(
    id          BIGINT       NOT NULL PRIMARY KEY,
    username    VARCHAR(255) NOT NULL,
    total_price INT          NOT NULL,
    address     VARCHAR,
    phone       VARCHAR(255),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS orders_items (
    quantity INT NOT NULL ,
    order_id BIGINT NOT NULL REFERENCES orders(id),
    product_id BIGINT NOT NULL REFERENCES products(id),
    price_per_product INT NOT NULL ,
    price INT NOT NULL ,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(order_id, product_id)
)