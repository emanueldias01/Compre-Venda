package br.com.emanueldias.CompreVenda.pagamento.dto;

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
