package br.com.gabrielrps.compras.graphql;

import br.com.gabrielrps.compras.input.ProdutoInput;
import br.com.gabrielrps.compras.model.Produto;
import br.com.gabrielrps.compras.service.ProdutoService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private ProdutoService produtoService;

    public Produto produto(Long id){
        return produtoService.findById(id);
    }

    public List<Produto> produtos(){
        return produtoService.getAllProdutos();
    }

    public Produto saveProduto(ProdutoInput input){
        ModelMapper modelMapper = new ModelMapper();
        Produto c = modelMapper.map(input, Produto.class);

        return produtoService.saveProduto(c);
    }

    public Boolean deleteProduto(Long id){
        if(produtoService.findById(id) != null){
            produtoService.deleteById(id);
            return true;
        }

        return false;
    }
}
