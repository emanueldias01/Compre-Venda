package br.com.emanueldias.pedidos.pedido.dto;

import br.com.emanueldias.pedidos.produto.dto.ProdutoRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class PedidoRequestDTO {

        private String nome;

        private List<ProdutoRequestDTO> itens = new ArrayList<>();

}
