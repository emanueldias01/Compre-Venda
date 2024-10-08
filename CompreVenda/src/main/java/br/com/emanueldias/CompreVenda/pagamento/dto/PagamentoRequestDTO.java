package br.com.emanueldias.CompreVenda.pagamento.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PagamentoRequestDTO {

    private String nome;

    private BigDecimal valor;

    private String codigo;

    private Long pedidoId;
}
