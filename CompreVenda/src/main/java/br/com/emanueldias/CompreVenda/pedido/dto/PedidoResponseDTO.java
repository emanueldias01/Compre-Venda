package br.com.emanueldias.CompreVenda.pedido.dto;

import br.com.emanueldias.CompreVenda.pedido.model.Pedido;
import br.com.emanueldias.CompreVenda.pedido.model.Status;
import br.com.emanueldias.CompreVenda.produto.model.Produto;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PedidoResponseDTO {
    private Long id;

    private LocalDateTime dataPedido;

    private Status status;

    private String nome;

    private List<Produto> itens = new ArrayList<>();

    private BigDecimal preco;

    public PedidoResponseDTO(Pedido pedido){
        this.id = pedido.getId();
        this.nome = pedido.getNome();
        this.dataPedido = pedido.getDataPedido();
        this.status = pedido.getStatus();
        this.preco = pedido.getPreco();
    }

}
