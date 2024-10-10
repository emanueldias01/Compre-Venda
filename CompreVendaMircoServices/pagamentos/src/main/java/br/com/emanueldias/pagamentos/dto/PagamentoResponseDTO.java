package br.com.emanueldias.pagamentos.dto;

import br.com.emanueldias.pagamentos.model.Pagamento;
import br.com.emanueldias.pagamentos.model.Status;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class PagamentoResponseDTO {

    private Long id;

    private BigDecimal valor;

    private String nome;

    private String codigo;

    private LocalDate expiracao;

    private Status status;

    private Long pedidoId;

    public PagamentoResponseDTO(Pagamento pagamento){
        this.id = pagamento.getId();
        this.valor = pagamento.getValor();
        this.codigo = pagamento.getCodigo();
        this.nome = pagamento.getNome();
        this.expiracao = pagamento.getExpiracao();
        this.status = pagamento.getStatus();
        this.pedidoId = pagamento.getPedidoId();
    }

    @Override
    public String toString() {
        return "PagamentoResponseDTO{" +
                "codigo='" + codigo + '\'' +
                ", id=" + id +
                ", valor=" + valor +
                ", nome='" + nome + '\'' +
                ", expiracao=" + expiracao +
                ", status=" + status +
                ", pedidoId=" + pedidoId +
                '}';
    }
}
