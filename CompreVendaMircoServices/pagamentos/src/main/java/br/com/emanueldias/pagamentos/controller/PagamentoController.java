package br.com.emanueldias.pagamentos.controller;

import br.com.emanueldias.pagamentos.dto.PagamentoRequestDTO;
import br.com.emanueldias.pagamentos.dto.PagamentoResponseDTO;
import br.com.emanueldias.pagamentos.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<PagamentoResponseDTO> create(@RequestBody PagamentoRequestDTO dto, UriComponentsBuilder uriComponentsBuilder){
        PagamentoResponseDTO response = pagamentoService.create(dto);
        URI uri = uriComponentsBuilder.path("{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/paga/{id}")
    public ResponseEntity<PagamentoResponseDTO> pagaPagamento(@PathVariable Long id){
        return ResponseEntity.ok(pagamentoService.pagaPagamento(id));
    }

    @PutMapping("/cancela/{id}")
    public ResponseEntity<PagamentoResponseDTO> cancelaPagamento(@PathVariable Long id){
        return ResponseEntity.ok(pagamentoService.cancelaPagamento(id));
    }
}
