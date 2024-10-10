package br.com.emanueldias.pedidos.pedido.dto;

import br.com.emanueldias.pedidos.pedido.model.Pedido;
import br.com.emanueldias.pedidos.pedido.model.Status;
import br.com.emanueldias.pedidos.produto.dto.ProdutoResponseDTO;
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

    private List<ProdutoResponseDTO> itens = new ArrayList<>();

    private BigDecimal preco;

    public PedidoResponseDTO(Pedido pedido){
        this.id = pedido.getId();
        this.nome = pedido.getNome();
        this.dataPedido = pedido.getDataPedido();
        this.status = pedido.getStatus();
        this.preco = pedido.getPreco();
        this.itens = pedido.getItens().stream().map(ProdutoResponseDTO::new).toList();
    }

}
