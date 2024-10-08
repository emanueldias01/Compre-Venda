package br.com.emanueldias.CompreVenda.pedido.repository;

import br.com.emanueldias.CompreVenda.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
