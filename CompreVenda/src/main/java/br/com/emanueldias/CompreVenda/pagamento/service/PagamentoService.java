package br.com.emanueldias.CompreVenda.pagamento.service;

import br.com.emanueldias.CompreVenda.pagamento.dto.PagamentoRequestDTO;
import br.com.emanueldias.CompreVenda.pagamento.dto.PagamentoResponseDTO;
import br.com.emanueldias.CompreVenda.pagamento.model.Pagamento;
import br.com.emanueldias.CompreVenda.pagamento.model.Status;
import br.com.emanueldias.CompreVenda.pagamento.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    PagamentoRepository pagamentoRepository;

    public List<PagamentoResponseDTO> listAll(){
        return pagamentoRepository.findAll().stream().map(PagamentoResponseDTO::new).toList();
    }

    public PagamentoResponseDTO create(@RequestBody PagamentoRequestDTO dto){
        Pagamento pagamento = new Pagamento(dto);

        pagamentoRepository.save(pagamento);

        return new PagamentoResponseDTO(pagamento);
    }

    public PagamentoResponseDTO cancelaPagamento(Long id){
        Pagamento pagamento = pagamentoRepository.getReferenceById(id);
        pagamento.setStatus(Status.CANCELADO);

        return new PagamentoResponseDTO(pagamento);
    }
}
