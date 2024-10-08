package br.com.emanueldias.CompreVenda.pedido.controller;

import br.com.emanueldias.CompreVenda.pedido.dto.PedidoRequestDTO;
import br.com.emanueldias.CompreVenda.pedido.dto.PedidoResponseDTO;
import br.com.emanueldias.CompreVenda.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listAll(){
        return ResponseEntity.ok(pedidoService.listAll());
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> create(@RequestBody PedidoRequestDTO dto){
        return ResponseEntity.ok(pedidoService.create(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> cancelaPedido(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.cancelarPedido(id));
    }


}
