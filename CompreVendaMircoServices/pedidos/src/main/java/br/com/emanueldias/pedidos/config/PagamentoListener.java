package br.com.emanueldias.pedidos.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {

    @RabbitListener(queues = "pagamento.concluido")
    public void pagamentoConcluidoListener(String s){
        System.out.println(s);
    }
}
