package br.com.emanueldias.CompreVenda.produto.repository;

import br.com.emanueldias.CompreVenda.produto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
