CREATE TABLE tab_produto(
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL,
    preco NUMERIC(11,2) NOT NULL,
    pedido_id BIGINT,
    FOREIGN KEY (pedido_id) REFERENCES tab_pedidos(id)
);