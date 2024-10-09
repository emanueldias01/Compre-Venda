package br.com.emanueldias.pedidos.produto.repository;

import br.com.emanueldias.pedidos.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
