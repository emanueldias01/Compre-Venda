package br.com.emanueldias.pedidos.httpClient;

import br.com.emanueldias.pedidos.pedido.model.Pedido;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RequestCriaPagamento {
    private String nome;
    private BigDecimal valor;
    private Long pedidoId;


    public RequestCriaPagamento(){

    }

    public static RequestCriaPagamento fromPedido(Pedido pedido) {
        RequestCriaPagamento req = new RequestCriaPagamento();
        req.nome = pedido.getNome();
        req.valor = pedido.getPreco();
        req.pedidoId = pedido.getId();
        return req;
    }
}
