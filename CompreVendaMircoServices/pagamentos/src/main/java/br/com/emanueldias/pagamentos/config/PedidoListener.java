package br.com.emanueldias.pagamentos.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {
    @RabbitListener(queues = "pedido.criado")
    public void pedidoCriadoListener(String s){
        System.out.println(s);
    }
}
