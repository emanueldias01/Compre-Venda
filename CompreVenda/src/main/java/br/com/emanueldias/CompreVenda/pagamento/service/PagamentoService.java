package br.com.emanueldias.CompreVenda.pagamento.service;

import br.com.emanueldias.CompreVenda.pagamento.dto.PagamentoRequestDTO;
import br.com.emanueldias.CompreVenda.pagamento.dto.PagamentoResponseDTO;
import br.com.emanueldias.CompreVenda.pagamento.model.Pagamento;
import br.com.emanueldias.CompreVenda.pagamento.model.Status;
import br.com.emanueldias.CompreVenda.pagamento.repository.PagamentoRepository;
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

        return new PagamentoResponseDTO(pagamento);
    }

    @Transactional
    public PagamentoResponseDTO pagaPagamento(Long id){
        Pagamento pagamento = pagamentoRepository.getReferenceById(id);
        pagamento.setStatus(Status.PAGO);

        pagamentoRepository.save(pagamento);

        return new PagamentoResponseDTO(pagamento);
    }
}
