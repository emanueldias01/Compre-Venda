package br.com.emanueldias.pedidos.config;

import br.com.emanueldias.pedidos.pedido.service.PedidoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoListener {

    @Autowired
    PedidoService pedidoService;

    @RabbitListener(queues = "pagamento.aprovado")
    public void pagamentoAprovadoListener(String s){

        Long id = getPedidoId(s);

        pedidoService.pagaPedido(id);

    }

    private Long getPedidoId(String s){
        char[] charsParts = s.toCharArray();
        String id = "";

        for(char c : charsParts){
            if(Character.isDigit(c)){
                id = id.concat(String.valueOf(c));
            }
        }
        return Long.parseLong(id);
    }
}
