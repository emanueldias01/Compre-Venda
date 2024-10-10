package br.com.emanueldias.pagamentos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PagamentoRequestDTO {

    private String nome;

    private BigDecimal valor;

    private Long pedidoId;
}
