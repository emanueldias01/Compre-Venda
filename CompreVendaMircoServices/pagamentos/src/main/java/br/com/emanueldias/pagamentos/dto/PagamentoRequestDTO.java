package br.com.emanueldias.pagamentos.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PagamentoRequestDTO {

    private String nome;

    private BigDecimal valor;

    private Long pedidoId;
}
