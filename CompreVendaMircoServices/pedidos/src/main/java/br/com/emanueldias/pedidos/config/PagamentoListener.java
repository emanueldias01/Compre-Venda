package br.com.emanueldias.pedidos.config;

import br.com.emanueldias.pedidos.pedido.service.PedidoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {

    @Autowired
    PedidoService pedidoService;

    @RabbitListener(queues = "pagamento.concluido")
    public void pagamentoConcluidoListener(String s){
        Long pedidoId = getIdPedido(s);

        pedidoService.pagaPedido(pedidoId);
    }

    @RabbitListener(queues = "pagamento.cancelado")
    public void pagamentoCanceladoListener(String s){
        Long pedidoId = getIdPedido(s);

        pedidoService.cancelaPedido(pedidoId);
    }

    private Long getIdPedido(String s){
        char[] parts = s.toCharArray();
        String id = "";
        for(char c : parts){
            if(Character.isDigit(c)){
                id = id.concat(String.valueOf(c));
            }
        }
        return Long.parseLong(id);
    }
}
