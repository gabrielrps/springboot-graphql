package br.com.gabrielrps.compras.resolver;

import br.com.gabrielrps.compras.model.Produto;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

@Component
public class ProdutoResolver implements GraphQLResolver<Produto> {

    public String valorReais(Produto produto){
        return "R$ " + produto.getValor();
    }


}
