package br.com.gabrielrps.compras.service;

import br.com.gabrielrps.compras.model.Produto;
import br.com.gabrielrps.compras.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto findById(Long id){
        return produtoRepository.findById(id).orElse(null);
    }

    public List<Produto> getAllProdutos(){
        return produtoRepository.findAll();
    }

    public Produto saveProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public Boolean deleteById(Long id){
        if(produtoRepository.findById(id).isPresent()){
            produtoRepository.deleteById(id);
            return true;
        }

        return false;
    }

}
