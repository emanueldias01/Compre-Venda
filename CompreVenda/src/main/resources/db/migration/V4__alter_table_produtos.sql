ALTER TABLE tab_produto
ADD COLUMN pedido_id BIGINT,
ADD CONSTRAINT fk_pedido
FOREIGN KEY (pedido_id) REFERENCES tab_pedidos(id);