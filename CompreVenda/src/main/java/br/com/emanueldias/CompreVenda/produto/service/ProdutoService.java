package br.com.emanueldias.CompreVenda.produto.service;

import br.com.emanueldias.CompreVenda.produto.dto.ProdutoRequestDTO;
import br.com.emanueldias.CompreVenda.produto.dto.ProdutoResponseDTO;
import br.com.emanueldias.CompreVenda.produto.dto.ProdutoUpdateDTO;
import br.com.emanueldias.CompreVenda.produto.model.Produto;
import br.com.emanueldias.CompreVenda.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public List<ProdutoResponseDTO> listAll(){
        return produtoRepository.findAll().stream().map(ProdutoResponseDTO::new).toList();
    }

    public ProdutoResponseDTO create(ProdutoRequestDTO dto){
        Produto produto = produtoRepository.getReferenceById(dto.getId());
        produto.updateInfo(dto);
        produtoRepository.save(produto);

        return new ProdutoResponseDTO(produto);
    }

    public ProdutoResponseDTO update(ProdutoUpdateDTO dto){
        Produto produto = new Produto(dto);
        produtoRepository.save(produto);

        return new ProdutoResponseDTO(produto);
    }

    public void delete(Long id){
        produtoRepository.deleteById(id);
    }
}
