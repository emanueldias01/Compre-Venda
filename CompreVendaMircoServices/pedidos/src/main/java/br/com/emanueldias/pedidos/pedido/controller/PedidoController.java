package br.com.emanueldias.pedidos.pedido.controller;

import br.com.emanueldias.pedidos.pedido.dto.PedidoRequestDTO;
import br.com.emanueldias.pedidos.pedido.dto.PedidoResponseDTO;
import br.com.emanueldias.pedidos.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<PedidoResponseDTO> create(@RequestBody PedidoRequestDTO dto, UriComponentsBuilder uriComponentsBuilder){
        PedidoResponseDTO pedido = pedidoService.create(dto);

        URI uri = uriComponentsBuilder.path("{id}").buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).body(pedido);
    }

    @PutMapping("/pagar/{id}")
    public ResponseEntity<PedidoResponseDTO> pagaPedido(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.pagaPedido(id));
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<PedidoResponseDTO> cancelaPedido(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.cancelaPedido(id));
    }
}
