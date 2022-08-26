CREATE table orders
(
    id          bigserial primary key,
    user_id     bigint not null references users (id),
    total_price int    not null,
    address     varchar,
    phone       varchar(255),
    created_at  timestamp,
    updated_at  timestamp
);

CREATE TABLE order_items
(
    id                bigserial primary key,
    product_id        bigint not null references product_table (id),
    quantity          int    not null,
    order_id          bigint not null references orders (id),
    price_per_product int    not null,
    price             int    not null,
    created_at        timestamp,
    updated_at        timestamp
);
