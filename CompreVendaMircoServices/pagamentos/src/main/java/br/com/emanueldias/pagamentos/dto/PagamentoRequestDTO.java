package br.com.emanueldias.pagamentos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoRequestDTO {

    private String nome;

    private BigDecimal valor;

    private Long pedidoId;
}
