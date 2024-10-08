package br.com.emanueldias.CompreVenda.produto.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProdutoRequestDTO {

    private Long id;
    private String nome;

    private String descricao;

    private Integer quantidade;

    private BigDecimal preco;

}
