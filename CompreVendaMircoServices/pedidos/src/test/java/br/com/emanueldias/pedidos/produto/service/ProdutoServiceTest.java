package br.com.emanueldias.pedidos.produto.service;

import br.com.emanueldias.pedidos.httpClient.PagamentoClient;
import br.com.emanueldias.pedidos.pedido.model.Pedido;
import br.com.emanueldias.pedidos.produto.dto.ProdutoRequestDTO;
import br.com.emanueldias.pedidos.produto.dto.ProdutoResponseDTO;
import br.com.emanueldias.pedidos.produto.model.Produto;
import br.com.emanueldias.pedidos.produto.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

class ProdutoServiceTest {

    @Mock
    ProdutoRepository produtoRepository;

    @InjectMocks
    ProdutoService produtoService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Cria produto corretamente")
    void criaProduto() throws IllegalAccessException {
        ProdutoRequestDTO p1 = new ProdutoRequestDTO("nome", "desc", 1, BigDecimal.TEN);

        ProdutoResponseDTO responseDTO = produtoService.create(p1);

        Produto produto = new Produto(responseDTO);

        List<Produto> list = Arrays.asList(produto);

        when(produtoRepository.findAll()).thenReturn(list);
    }

    @Test
    @DisplayName("Falha ao criar produto devido a falta de nome")
    void criaProdutoFailNome(){
        ProdutoRequestDTO p1 = new ProdutoRequestDTO(null, "desc", 1, BigDecimal.TEN);

        Assertions.assertThrows(IllegalArgumentException.class, () -> produtoService.create(p1));

    }

    @Test
    @DisplayName("Falha ao criar produto devido a falta de descricao")
    void criaProdutoFailDesc(){
        ProdutoRequestDTO p1 = new ProdutoRequestDTO("nome", null, 1, BigDecimal.TEN);

        Assertions.assertThrows(IllegalArgumentException.class, () -> produtoService.create(p1));

    }

    @Test
    @DisplayName("Falha ao criar produto devido a falta de quantidade")
    void criaProdutoFailQuantIncomp(){
        ProdutoRequestDTO p1 = new ProdutoRequestDTO("nome", "desc", 0, BigDecimal.TEN);

        Assertions.assertThrows(IllegalArgumentException.class, () -> produtoService.create(p1));

    }

    @Test
    @DisplayName("Falha ao criar produto devido a falta de preco")
    void criaProdutoFailPreco(){
        ProdutoRequestDTO p1 = new ProdutoRequestDTO("nome", "desc", 0, null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> produtoService.create(p1));

    }

    @Test
    @DisplayName("Falha ao criar produto devido a preco zero")
    void criaProdutoFailPrecoIncomp(){
        ProdutoRequestDTO p1 = new ProdutoRequestDTO("nome", "desc", 0, BigDecimal.ZERO);

        Assertions.assertThrows(IllegalArgumentException.class, () -> produtoService.create(p1));

    }

    @Test
    @DisplayName("Falha ao criar produto devido a quantidade nula")
    void criaProdutoFailQuantNull(){
        ProdutoRequestDTO p1 = new ProdutoRequestDTO("nome", "desc", null, BigDecimal.TEN);

        Assertions.assertThrows(NullPointerException.class, () -> produtoService.create(p1));

    }

}