package br.com.emanueldias.CompreVenda.pedido.model;

import br.com.emanueldias.CompreVenda.pedido.dto.PedidoRequestDTO;
import br.com.emanueldias.CompreVenda.produto.model.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tab_pedidos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime dataPedido;

    @NotNull
    private String nome;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="pedido")
    private List<Produto> itens = new ArrayList<>();

    @NotNull
    private BigDecimal preco;

    public Pedido(PedidoRequestDTO dto) {
        this.nome = dto.getNome();
        this.dataPedido = LocalDateTime.now();
        this.itens = dto.getItens();
        this.status = Status.REALIZADO;
    }

    public void setPreco(@NotNull BigDecimal preco) {
        this.preco = preco;
    }

    public void setStatus(@NotBlank Status status) {
        this.status = status;
    }
}
