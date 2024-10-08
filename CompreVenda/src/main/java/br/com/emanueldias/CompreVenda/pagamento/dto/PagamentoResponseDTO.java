package br.com.emanueldias.CompreVenda.pagamento.dto;

import br.com.emanueldias.CompreVenda.pagamento.model.Pagamento;
import br.com.emanueldias.CompreVenda.pagamento.model.Status;
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

    }
}
