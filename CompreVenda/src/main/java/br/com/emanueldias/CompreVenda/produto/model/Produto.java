package br.com.emanueldias.CompreVenda.produto.model;

import br.com.emanueldias.CompreVenda.produto.dto.ProdutoRequestDTO;
import br.com.emanueldias.CompreVenda.produto.dto.ProdutoUpdateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "tab_produtos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 5, max = 100)
    private String nome;

    @NotBlank
    @Size(min = 10, max = 255)
    private String descricao;

    @NotNull
    @Size(min = 1)
    private Integer quantidade;

    @NotNull
    private BigDecimal preco;


    public Produto(ProdutoRequestDTO dto) {
        this.nome = dto.getNome();
        this.descricao = dto.getDescricao();
        this.quantidade = dto.getQuantidade();
        this.preco = dto.getPreco();
    }

    public Produto(ProdutoUpdateDTO dto){
        this.id = dto.getId();
        this.nome = dto.getNome();
        this.descricao = dto.getDescricao();
        this.quantidade = dto.getQuantidade();
        this.preco = dto.getPreco();
    }

    public void updateInfo(ProdutoRequestDTO dto) {
        if(dto.getNome() != null){
            this.nome = dto.getNome();
        }

        if(dto.getDescricao() != null){
            this.descricao = dto.getDescricao();
        }

        if(dto.getQuantidade() != null){
            this.quantidade = dto.getQuantidade();
        }

        if(dto.getPreco() != null){
            this.preco = dto.getPreco();
        }
    }
}