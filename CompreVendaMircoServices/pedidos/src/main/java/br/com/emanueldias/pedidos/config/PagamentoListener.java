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
        char[] parts = s.toCharArray();
        String id = "";
        for(char c : parts){
            if(Character.isDigit(c)){
                id = id.concat(String.valueOf(c));
            }
        }
        Long pedidoId = Long.parseLong(id);

        pedidoService.pagaPedido(pedidoId);
    }
}
