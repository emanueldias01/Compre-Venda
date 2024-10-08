CREATE TABLE tab_pedidos (
    id SERIAL PRIMARY KEY,
    data_pedido TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL
);