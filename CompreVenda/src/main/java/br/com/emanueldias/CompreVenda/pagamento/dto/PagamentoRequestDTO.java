package br.com.emanueldias.CompreVenda.pagamento.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class PagamentoRequestDTO {

    private String nome;

    private BigDecimal valor;

    private String codigo;

    private LocalDate expiracao;

    private Long pedidoId;
}
