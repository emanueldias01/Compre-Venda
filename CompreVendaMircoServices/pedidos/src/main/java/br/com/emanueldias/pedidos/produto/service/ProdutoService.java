package br.com.emanueldias.pedidos.produto.service;

import br.com.emanueldias.pedidos.produto.dto.ProdutoRequestDTO;
import br.com.emanueldias.pedidos.produto.dto.ProdutoResponseDTO;
import br.com.emanueldias.pedidos.produto.model.Produto;
import br.com.emanueldias.pedidos.produto.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoResponseDTO create(ProdutoRequestDTO dto) throws IllegalArgumentException {

        if(dto.getNome() == null || dto.getQuantidade() == 0 || dto.getDescricao() == null || dto.getPreco() == null || dto.getPreco().equals(BigDecimal.ZERO)){
            throw new IllegalArgumentException();
        }

        Produto produto = new Produto(dto);
        produtoRepository.save(produto);

        return new ProdutoResponseDTO(produto);
    }

    @Transactional
    public void delete(Long id){
        produtoRepository.deleteById(id);
    }
}
