package br.com.emanueldias.pedidos.pedido.repository;

import br.com.emanueldias.pedidos.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
