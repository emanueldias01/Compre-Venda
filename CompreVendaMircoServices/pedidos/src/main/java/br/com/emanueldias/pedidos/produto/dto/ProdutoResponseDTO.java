package br.com.emanueldias.pedidos.produto.dto;

import br.com.emanueldias.pedidos.produto.model.Produto;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "ProdutoResponseDTO{" +
                "descricao='" + descricao + '\'' +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoResponseDTO)) return false;
        ProdutoResponseDTO that = (ProdutoResponseDTO) o;
        return quantidade == that.quantidade &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(preco, that.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, nome, quantidade, preco);
    }
}
