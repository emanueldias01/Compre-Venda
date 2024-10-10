package br.com.emanueldias.pedidos.pedido.service;

import br.com.emanueldias.pedidos.pedido.dto.PedidoRequestDTO;
import br.com.emanueldias.pedidos.pedido.dto.PedidoResponseDTO;
import br.com.emanueldias.pedidos.pedido.model.Pedido;
import br.com.emanueldias.pedidos.pedido.model.Status;
import br.com.emanueldias.pedidos.pedido.repository.PedidoRepository;
import br.com.emanueldias.pedidos.produto.dto.ProdutoRequestDTO;
import br.com.emanueldias.pedidos.produto.dto.ProdutoResponseDTO;
import br.com.emanueldias.pedidos.produto.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PedidoServiceTest {

    @Mock
    PedidoRepository pedidoRepository;

    @InjectMocks
    PedidoService pedidoService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("lista todos os pedidos")
    void listPedidos(){

        var dto1 = new PedidoRequestDTO("nome1", new ArrayList<>());
        var dto2 = new PedidoRequestDTO("nome2", new ArrayList<>());

        Pedido p1 = new Pedido(dto1);
        Pedido p2 = new Pedido(dto2);

        List<PedidoResponseDTO> list = Arrays.asList(new PedidoResponseDTO(p1), new PedidoResponseDTO(p2));

        pedidoService.create(dto1);
        pedidoService.create(dto2);

        when(pedidoService.listAll()).thenReturn(list);

    }

    @Test
    @DisplayName("cria pedido")
    void criaPedido(){
        PedidoRequestDTO dto = new PedidoRequestDTO("nome", new ArrayList<>());

        Pedido pedido = new Pedido(dto);

        when(pedidoService.create(dto)).thenReturn(new PedidoResponseDTO(pedido));
    }

    @Test
    @DisplayName("valida valor do pedido quando criado")
    void validaValorDoPedido(){

        List<ProdutoRequestDTO> itens = new ArrayList<>();
        var p1 = new ProdutoRequestDTO("nome", "desc", 1, new BigDecimal(10));
        var p2 = new ProdutoRequestDTO("nome2", "desc2", 1, new BigDecimal(20));

        itens.add(p1);
        itens.add(p2);

        PedidoRequestDTO dto = new PedidoRequestDTO("nome", itens);

        PedidoResponseDTO result = pedidoService.create(dto);

        assertThat(result.getPreco()).isEqualTo(new BigDecimal(30));
    }

    @Test
    @DisplayName("Valida itens do pedido")
    void validaItensDoPedido() {
        List<ProdutoRequestDTO> itens = new ArrayList<>();
        var p1 = new ProdutoRequestDTO("nome", "desc", 1, new BigDecimal(10));
        var p2 = new ProdutoRequestDTO("nome2", "desc2", 1, new BigDecimal(20));

        itens.add(p1);
        itens.add(p2);

        PedidoRequestDTO pedidoRequestDTO = new PedidoRequestDTO("nome", itens);

        PedidoResponseDTO result = new PedidoResponseDTO(new Pedido(pedidoRequestDTO));

        when(pedidoService.create(pedidoRequestDTO)).thenReturn(result);

        List<ProdutoResponseDTO> produtoResponseDTOList= new ArrayList<>();
        produtoResponseDTOList.add(new ProdutoResponseDTO(new Produto(p1)));
        produtoResponseDTOList.add(new ProdutoResponseDTO(new Produto(p2)));

        assertThat(result.getItens()).containsExactlyInAnyOrderElementsOf(produtoResponseDTOList);

    }

    @Test
    @DisplayName("Valida metodo de pagar pedido")
    void validaPagamento(){
        List<ProdutoRequestDTO> itens = new ArrayList<>();
        var p1 = new ProdutoRequestDTO("nome", "desc", 1, new BigDecimal(10));
        var p2 = new ProdutoRequestDTO("nome2", "desc2", 1, new BigDecimal(20));

        itens.add(p1);
        itens.add(p2);

        PedidoRequestDTO pedidoRequestDTO = new PedidoRequestDTO("nome", itens);

        PedidoResponseDTO pedidoResponseDTO = pedidoService.create(pedidoRequestDTO);

        Pedido pedido = new Pedido();
        when(pedidoRepository.getReferenceById(pedidoResponseDTO.getId())).thenReturn(pedido);

        pedidoService.pagaPedido(pedido.getId());

        assertThat(pedido.getStatus()).isEqualTo(Status.PAGO);

    }

    @Test
    @DisplayName("valida metodo de cancelar pedido")
    void validaCancelamento(){
        List<ProdutoRequestDTO> itens = new ArrayList<>();
        var p1 = new ProdutoRequestDTO("nome", "desc", 1, new BigDecimal(10));
        var p2 = new ProdutoRequestDTO("nome2", "desc2", 1, new BigDecimal(20));

        itens.add(p1);
        itens.add(p2);

        PedidoRequestDTO pedidoRequestDTO = new PedidoRequestDTO("nome", itens);

        PedidoResponseDTO pedidoResponseDTO = pedidoService.create(pedidoRequestDTO);

        Pedido pedido = new Pedido();
        when(pedidoRepository.getReferenceById(pedidoResponseDTO.getId())).thenReturn(pedido);

        pedidoService.cancelaPedido(pedido.getId());

        assertThat(pedido.getStatus()).isEqualTo(Status.CANCELADO);
    }
}