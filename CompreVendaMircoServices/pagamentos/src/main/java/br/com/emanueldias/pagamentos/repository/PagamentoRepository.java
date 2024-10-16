package br.com.emanueldias.pagamentos.repository;

import br.com.emanueldias.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    @Query(value = "SELECT * FROM tab_pagamentos WHERE pedido_id=:id", nativeQuery = true)
    Optional<Pagamento> buscaPedidoId(@Param("id") Long id);
}
