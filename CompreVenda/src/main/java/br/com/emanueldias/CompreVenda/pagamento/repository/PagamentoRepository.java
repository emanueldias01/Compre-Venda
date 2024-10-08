package br.com.emanueldias.CompreVenda.pagamento.repository;

import br.com.emanueldias.CompreVenda.pagamento.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
