package br.com.emanueldias.pedidos.produto.model;

import br.com.emanueldias.pedidos.pedido.model.Pedido;
import br.com.emanueldias.pedidos.produto.dto.ProdutoRequestDTO;
import br.com.emanueldias.pedidos.produto.dto.ProdutoResponseDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @Min(1)
    private Integer quantidade;

    @NotNull
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto(ProdutoRequestDTO dto) {
        this.nome = dto.getNome();
        this.descricao = dto.getDescricao();
        this.quantidade = dto.getQuantidade();
        this.preco = dto.getPreco();
    }


    public Produto(ProdutoResponseDTO responseDTO) {
        this.nome = responseDTO.getNome();
        this.descricao = responseDTO.getDescricao();
        this.quantidade = responseDTO.getQuantidade();
        this.preco = responseDTO.getPreco();
    }
}
