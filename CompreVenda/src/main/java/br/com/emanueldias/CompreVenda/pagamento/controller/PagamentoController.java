package br.com.emanueldias.CompreVenda.pagamento.controller;

import br.com.emanueldias.CompreVenda.pagamento.dto.PagamentoRequestDTO;
import br.com.emanueldias.CompreVenda.pagamento.dto.PagamentoResponseDTO;
import br.com.emanueldias.CompreVenda.pagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> listAll(){
        return ResponseEntity.ok(pagamentoService.listAll());
    }

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> create(@RequestBody PagamentoRequestDTO dto){
        return ResponseEntity.ok(pagamentoService.create(dto));
    }

    @PatchMapping("/cancela/{id}")
    public ResponseEntity cancelaPagamento(@PathVariable Long id){
        return ResponseEntity.ok(pagamentoService.cancelaPagamento(id));
    }

    @PatchMapping("/paga/{id}")
    public ResponseEntity pagaPagamento(@PathVariable Long id){
        return ResponseEntity.ok(pagamentoService.pagaPagamento(id));
    }

}
