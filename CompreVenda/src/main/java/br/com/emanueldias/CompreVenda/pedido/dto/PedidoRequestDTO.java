package br.com.emanueldias.CompreVenda.pedido.dto;

import br.com.emanueldias.CompreVenda.produto.dto.ProdutoRequestDTO;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PedidoRequestDTO {

    private String nome;

    private List<ProdutoRequestDTO> itens = new ArrayList<>();
}
