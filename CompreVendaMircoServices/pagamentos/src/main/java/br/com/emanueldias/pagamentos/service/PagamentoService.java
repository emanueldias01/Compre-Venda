package br.com.emanueldias.pagamentos.service;

import br.com.emanueldias.pagamentos.dto.PagamentoRequestDTO;
import br.com.emanueldias.pagamentos.dto.PagamentoResponseDTO;
import br.com.emanueldias.pagamentos.model.Pagamento;
import br.com.emanueldias.pagamentos.model.Status;
import br.com.emanueldias.pagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PagamentoService {

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public List<PagamentoResponseDTO> listAll(){
        return pagamentoRepository.findAll().stream().map(PagamentoResponseDTO::new).toList();
    }

    @Transactional
    public PagamentoResponseDTO create(PagamentoRequestDTO dto){
        Pagamento pagamento = new Pagamento(dto);

        Random random = new Random();
        String codigo = String.valueOf(random.nextInt(100));
        pagamento.setCodigo(codigo);

        pagamentoRepository.save(pagamento);

        return new PagamentoResponseDTO(pagamento);
    }

    @Transactional
    public PagamentoResponseDTO cancelaPagamento(Long pedidoId){
        Optional<Pagamento> pagamentoRef = pagamentoRepository.buscaPedidoId(pedidoId);
        if(pagamentoRef.isPresent()){
            Pagamento pagamento = pagamentoRef.get();

            System.out.println(pagamento.getId() + "  " + pagamento.getNome());

        if(pagamento.getStatus() == Status.CANCELADO || pagamento.getStatus() == Status.PAGO){
            throw new IllegalCallerException("Impossível cancelar um pagamento já cancelado ou pago");
        }
        pagamento.setStatus(Status.CANCELADO);

        pagamentoRepository.save(pagamento);

        Message message = new Message(("pagamento cancelado || id do pedido: " + pagamento.getPedidoId()).getBytes());

        rabbitTemplate.send("pagamento.cancelado", message);

        return new PagamentoResponseDTO(pagamento);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public PagamentoResponseDTO pagaPagamento(Long id){
        Pagamento pagamento = pagamentoRepository.getReferenceById(id);
        if(pagamento.getStatus() == Status.PAGO || pagamento.getStatus() == Status.CANCELADO){
            throw new IllegalCallerException("Impossível pagar um pagamento já cancelado ou pago");
        }
        pagamento.setStatus(Status.PAGO);

        pagamentoRepository.save(pagamento);

        Message message = new Message(("pagamento pago || id do pedido: " + pagamento.getPedidoId()).getBytes());

        rabbitTemplate.send("pagamento.concluido", message);

        return new PagamentoResponseDTO(pagamento);
    }
}
