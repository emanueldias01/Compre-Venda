package br.com.emanueldias.pedidos.produto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProdutoRequestDTO {

    private String nome;

    private String descricao;

    private Integer quantidade;

    private BigDecimal preco;

}

