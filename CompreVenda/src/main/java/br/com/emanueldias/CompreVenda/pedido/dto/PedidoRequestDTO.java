package br.com.emanueldias.CompreVenda.pedido.dto;

import br.com.emanueldias.CompreVenda.pedido.model.Status;
import br.com.emanueldias.CompreVenda.produto.dto.ProdutoResponseDTO;
import br.com.emanueldias.CompreVenda.produto.model.Produto;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PedidoRequestDTO {

    private String nome;

    private List<ProdutoResponseDTO> itens = new ArrayList<>();
}
