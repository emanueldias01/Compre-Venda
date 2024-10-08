package br.com.emanueldias.CompreVenda.produto.controller;

import br.com.emanueldias.CompreVenda.produto.dto.ProdutoRequestDTO;
import br.com.emanueldias.CompreVenda.produto.dto.ProdutoResponseDTO;
import br.com.emanueldias.CompreVenda.produto.dto.ProdutoUpdateDTO;
import br.com.emanueldias.CompreVenda.produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> getAll(){
        return ResponseEntity.ok(produtoService.listAll());
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> create(@RequestBody @Valid ProdutoRequestDTO dto){
        return ResponseEntity.ok(produtoService.create(dto));
    }

    @PutMapping
    public ResponseEntity<ProdutoResponseDTO> update(@RequestBody @Valid ProdutoUpdateDTO dto){
        return ResponseEntity.ok(produtoService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
