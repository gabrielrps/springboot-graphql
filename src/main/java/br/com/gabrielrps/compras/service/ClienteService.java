package br.com.gabrielrps.compras.service;

import br.com.gabrielrps.compras.model.Cliente;
import br.com.gabrielrps.compras.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente findById(Long id){
        return clienteRepository.findById(id).orElse(null);
    }

    //@Cacheable("clientes")
    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }

    public Cliente saveCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Boolean deleteById(Long id){
        if(clienteRepository.findById(id).isPresent()){
            clienteRepository.deleteById(id);
            return true;
        }

        return false;
    }

}
