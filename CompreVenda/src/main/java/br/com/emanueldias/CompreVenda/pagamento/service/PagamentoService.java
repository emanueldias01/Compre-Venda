package br.com.emanueldias.CompreVenda.pagamento.service;

import br.com.emanueldias.CompreVenda.pagamento.dto.PagamentoRequestDTO;
import br.com.emanueldias.CompreVenda.pagamento.dto.PagamentoResponseDTO;
import br.com.emanueldias.CompreVenda.pagamento.model.Pagamento;
import br.com.emanueldias.CompreVenda.pagamento.model.Status;
import br.com.emanueldias.CompreVenda.pagamento.repository.PagamentoRepository;
import br.com.emanueldias.CompreVenda.pedido.model.Pedido;
import br.com.emanueldias.CompreVenda.pedido.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Random;

@Service
public class PagamentoService {

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    public List<PagamentoResponseDTO> listAll(){
        return pagamentoRepository.findAll().stream().map(PagamentoResponseDTO::new).toList();
    }

    @Transactional
    public PagamentoResponseDTO create(@RequestBody PagamentoRequestDTO dto){
        Pagamento pagamento = new Pagamento(dto);

        Random random = new Random();
        String codigo = String.valueOf(random.nextInt(100));
        pagamento.setCodigo(codigo);

        pagamentoRepository.save(pagamento);

        return new PagamentoResponseDTO(pagamento);
    }

    @Transactional
    public PagamentoResponseDTO cancelaPagamento(Long id){
        Pagamento pagamento = pagamentoRepository.getReferenceById(id);
        pagamento.setStatus(Status.CANCELADO);

        pagamentoRepository.save(pagamento);

        Pedido pedido = pedidoRepository.getReferenceById(pagamento.getPedidoId());

        pedido.setStatus(br.com.emanueldias.CompreVenda.pedido.model.Status.CANCELADO);

        return new PagamentoResponseDTO(pagamento);
    }

    @Transactional
    public PagamentoResponseDTO pagaPagamento(Long id){
        Pagamento pagamento = pagamentoRepository.getReferenceById(id);
        pagamento.setStatus(Status.PAGO);

        Pedido pedido = pedidoRepository.getReferenceById(pagamento.getPedidoId());

        pedido.setStatus(br.com.emanueldias.CompreVenda.pedido.model.Status.PAGO);

        pagamentoRepository.save(pagamento);

        return new PagamentoResponseDTO(pagamento);
    }
}
