package br.com.emanueldias.CompreVenda.produto.dto;

import br.com.emanueldias.CompreVenda.pedido.model.Status;
import br.com.emanueldias.CompreVenda.produto.model.Produto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProdutoResponseDTO {

    private Long id;

    private String nome;

    private String descricao;

    private Integer quantidade;

    private BigDecimal preco;

    public ProdutoResponseDTO(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.quantidade = produto.getQuantidade();
        this.preco = produto.getPreco();
    }


    public ProdutoResponseDTO(){

    }
}
