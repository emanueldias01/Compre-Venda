CREATE TABLE tab_pagamentos(
    id SERIAL PRIMARY KEY,
    valor NUMERIC(11,2),
    nome VARCHAR(100),
    codigo VARCHAR(3),
    expiracao DATE,
    status VARCHAR(20),
    pedido_id BIGINT
);