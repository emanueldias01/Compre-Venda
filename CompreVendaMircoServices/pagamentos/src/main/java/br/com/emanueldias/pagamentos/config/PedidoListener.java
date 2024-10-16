package br.com.emanueldias.pagamentos.config;

import br.com.emanueldias.pagamentos.dto.PagamentoRequestDTO;
import br.com.emanueldias.pagamentos.service.PagamentoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    @Autowired
    PagamentoService pagamentoService;

    @RabbitListener(queues = "pedido.criado")
    public void pedidoCriadoListener(String s) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PagamentoRequestDTO dto = objectMapper.readValue(s, PagamentoRequestDTO.class);
        System.out.println(dto);

        pagamentoService.create(dto);
    }

    @RabbitListener(queues = "pedido.cancelado")
    public void pedidoCanceladoListener(String s){

        Long id = getPedidoId(s);

        System.out.println(id);
        pagamentoService.cancelaPagamento(id);
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
