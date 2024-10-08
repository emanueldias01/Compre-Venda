package br.com.emanueldias.CompreVenda.pedido.service;

import br.com.emanueldias.CompreVenda.pedido.dto.PedidoRequestDTO;
import br.com.emanueldias.CompreVenda.pedido.dto.PedidoResponseDTO;
import br.com.emanueldias.CompreVenda.pedido.model.Pedido;
import br.com.emanueldias.CompreVenda.pedido.model.Status;
import br.com.emanueldias.CompreVenda.pedido.repository.PedidoRepository;
import br.com.emanueldias.CompreVenda.produto.dto.ProdutoRequestDTO;
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
        pedido.getItens().forEach(p -> p.setPedido(pedido));

        BigDecimal valor = BigDecimal.ZERO;

        for(ProdutoRequestDTO item : dto.getItens()){
            valor = valor.add(item.getPreco());
        }

        pedido.setPreco(valor);

        pedidoRepository.save(pedido);

        return new PedidoResponseDTO(pedido);
    }

    public PedidoResponseDTO cancelarPedido(Long id){
        Pedido pedido = pedidoRepository.getReferenceById(id);

        pedido.setStatus(Status.CANCELADO);

        pedidoRepository.save(pedido);

        return new PedidoResponseDTO(pedido);
    }
}
