package br.com.emanueldias.CompreVenda.produto.service;

import br.com.emanueldias.CompreVenda.produto.dto.ProdutoRequestDTO;
import br.com.emanueldias.CompreVenda.produto.dto.ProdutoResponseDTO;
import br.com.emanueldias.CompreVenda.produto.dto.ProdutoUpdateDTO;
import br.com.emanueldias.CompreVenda.produto.model.Produto;
import br.com.emanueldias.CompreVenda.produto.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
