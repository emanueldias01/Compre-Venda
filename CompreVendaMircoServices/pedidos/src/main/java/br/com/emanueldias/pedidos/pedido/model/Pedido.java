package br.com.emanueldias.pedidos.pedido.model;

import br.com.emanueldias.pedidos.produto.model.Produto;
import jakarta.persistence.*;
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

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="pedido", fetch = FetchType.EAGER)
    private List<Produto> itens = new ArrayList<>();

    @NotNull
    private BigDecimal preco;
}
