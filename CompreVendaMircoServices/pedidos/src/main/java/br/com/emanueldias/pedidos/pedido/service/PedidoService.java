package br.com.emanueldias.pedidos.pedido.service;

import br.com.emanueldias.pedidos.pedido.dto.PedidoRequestDTO;
import br.com.emanueldias.pedidos.pedido.dto.PedidoResponseDTO;
import br.com.emanueldias.pedidos.pedido.model.Pedido;
import br.com.emanueldias.pedidos.pedido.model.Status;
import br.com.emanueldias.pedidos.pedido.repository.PedidoRepository;
import br.com.emanueldias.pedidos.produto.dto.ProdutoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public List<PedidoResponseDTO> listAll(){
        return pedidoRepository.findAll().stream().map(PedidoResponseDTO::new).toList();
    }

    public PedidoResponseDTO create(PedidoRequestDTO dto){
        Pedido pedido = new Pedido(dto);

        BigDecimal valor = BigDecimal.ZERO;

        for(ProdutoRequestDTO item : dto.getItens()){
            valor = valor.add(item.getPreco());
        }

        pedido.setPreco(valor);

        pedidoRepository.save(pedido);

        return new PedidoResponseDTO(pedido);
    }

    public PedidoResponseDTO cancelaPedido(Long id){
        Pedido pedido = pedidoRepository.getReferenceById(id);
        pedido.setStatus(Status.CANCELADO);
        pedidoRepository.save(pedido);

        return new PedidoResponseDTO(pedido);
    }

    public PedidoResponseDTO pagaPedido(Long id){
        Pedido pedido = pedidoRepository.getReferenceById(id);
        pedido.setStatus(Status.PAGO);
        pedidoRepository.save(pedido);

        return new PedidoResponseDTO(pedido);
    }
}
