package br.com.emanueldias.pedidos.produto.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProdutoRequestDTO {

    private String nome;

    private String descricao;

    private Integer quantidade;

    private BigDecimal preco;

}

