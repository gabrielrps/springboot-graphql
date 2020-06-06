package br.com.gabrielrps.compras.resolver;

import br.com.gabrielrps.compras.model.Cliente;
import br.com.gabrielrps.compras.model.Compra;
import br.com.gabrielrps.compras.model.Produto;
import br.com.gabrielrps.compras.service.ClienteService;
import br.com.gabrielrps.compras.service.ProdutoService;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompraResolver implements GraphQLResolver<Compra> {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    public Cliente cliente(Compra compra){
        return clienteService.findById(compra.getCliente().getId());
    }

    public Produto produto(Compra compra){
        return produtoService.findById(compra.getProduto().getId());
    }



}
