package br.com.gabrielrps.compras.resolver;

import br.com.gabrielrps.compras.model.Cliente;
import br.com.gabrielrps.compras.model.Compra;
import br.com.gabrielrps.compras.service.CompraService;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteResolver implements GraphQLResolver<Cliente> {

    @Autowired
    private CompraService compraService;

    public List<Compra> compras(Cliente cliente){
        return compraService.findAllByCliente(cliente);
    }

}
