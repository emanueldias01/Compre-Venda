package br.com.emanueldias.pedidos.pedido.service;

import br.com.emanueldias.pedidos.config.PagamentoListener;
import br.com.emanueldias.pedidos.httpClient.PagamentoClient;
import br.com.emanueldias.pedidos.httpClient.RequestCriaPagamento;
import br.com.emanueldias.pedidos.pedido.dto.PedidoRequestDTO;
import br.com.emanueldias.pedidos.pedido.dto.PedidoResponseDTO;
import br.com.emanueldias.pedidos.pedido.model.Pedido;
import br.com.emanueldias.pedidos.pedido.model.Status;
import br.com.emanueldias.pedidos.pedido.repository.PedidoRepository;
import br.com.emanueldias.pedidos.produto.dto.ProdutoRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PagamentoClient pagamentoClient;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public List<PedidoResponseDTO> listAll(){
        return pedidoRepository.findAll().stream().map(PedidoResponseDTO::new).toList();
    }

    @Transactional
    public PedidoResponseDTO create(PedidoRequestDTO dto){
        Pedido pedido = new Pedido(dto);
        pedido.getItens().forEach(p -> p.setPedido(pedido));

        BigDecimal valor = BigDecimal.ZERO;

        for(ProdutoRequestDTO item : dto.getItens()){
            valor = valor.add(item.getPreco());
        }

        pedido.setPreco(valor);

        pedidoRepository.save(pedido);

        //pagamentoClient.criaPagamento(RequestCriaPagamento.fromPedido(pedido));
        rabbitTemplate.convertAndSend("",  "pedido.criado", RequestCriaPagamento.fromPedido(pedido));

        return new PedidoResponseDTO(pedido);
    }

    @Transactional
    public PedidoResponseDTO cancelaPedido(Long id){
        Pedido pedido = pedidoRepository.getReferenceById(id);
        if(pedido.getStatus() == Status.CANCELADO || pedido.getStatus() == Status.PAGO){
            throw new IllegalCallerException("Impossível cancelar um pedido já pago ou cancelado");
        }
        pedido.setStatus(Status.CANCELADO);
        pedidoRepository.save(pedido);
        pagamentoClient.cancelaPagamento(pedido.getId());

        return new PedidoResponseDTO(pedido);
    }

    @Transactional
    public PedidoResponseDTO pagaPedido(Long id){
        Pedido pedido = pedidoRepository.getReferenceById(id);
        if(pedido.getStatus() == Status.CANCELADO || pedido.getStatus() == Status.NAO_AUTORIZADO || pedido.getStatus() == Status.PAGO){
            throw new IllegalCallerException("Impossível pagar um pedido cancelado, nao autorizado ou que ja está pago");
        }
        pedido.setStatus(Status.PAGO);
        pedidoRepository.save(pedido);

        return new PedidoResponseDTO(pedido);
    }
}
