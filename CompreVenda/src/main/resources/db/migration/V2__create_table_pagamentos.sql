CREATE TABLE tab_pagamentos (
  id SERIAL PRIMARY KEY,
  valor NUMERIC(19, 2) NOT NULL,
  expiracao DATE DEFAULT NULL,
  codigo VARCHAR(3) DEFAULT NULL,
  status VARCHAR(255) NOT NULL,
  pedido_id BIGINT NOT NULL
);