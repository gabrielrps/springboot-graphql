package br.com.gabrielrps.compras.graphql;

import br.com.gabrielrps.compras.input.ClienteInput;
import br.com.gabrielrps.compras.model.Cliente;
import br.com.gabrielrps.compras.service.ClienteService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private ClienteService clienteService;

    public Cliente cliente(Long id){
        return clienteService.findById(id);
    }

    public List<Cliente> clientes(){
        return clienteService.getAllClientes();
    }

    public Cliente saveCliente(ClienteInput input){
        ModelMapper modelMapper = new ModelMapper();
        Cliente c = modelMapper.map(input, Cliente.class);

        return clienteService.saveCliente(c);
    }

    public Boolean deleteCliente(Long id){
        if(clienteService.findById(id) != null){
            clienteService.deleteById(id);
            return true;
        }

        return false;
    }
}
