package br.com.emanueldias.pedidos.produto.service;

import br.com.emanueldias.pedidos.produto.dto.ProdutoRequestDTO;
import br.com.emanueldias.pedidos.produto.dto.ProdutoResponseDTO;
import br.com.emanueldias.pedidos.produto.model.Produto;
import br.com.emanueldias.pedidos.produto.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoResponseDTO create(ProdutoRequestDTO dto){
        Produto produto = new Produto(dto);
        produtoRepository.save(produto);

        return new ProdutoResponseDTO(produto);
    }

    @Transactional
    public void delete(Long id){
        produtoRepository.deleteById(id);
    }
}
