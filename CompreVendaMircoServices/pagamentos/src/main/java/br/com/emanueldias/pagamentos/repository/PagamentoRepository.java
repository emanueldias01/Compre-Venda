package br.com.emanueldias.pagamentos.repository;

import br.com.emanueldias.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
