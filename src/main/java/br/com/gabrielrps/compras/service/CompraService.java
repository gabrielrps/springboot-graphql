package br.com.gabrielrps.compras.service;

import br.com.gabrielrps.compras.exceptions.DomainException;
import br.com.gabrielrps.compras.input.CompraRelatorio;
import br.com.gabrielrps.compras.model.Cliente;
import br.com.gabrielrps.compras.model.Compra;
import br.com.gabrielrps.compras.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public Compra findById(Long id){
        return compraRepository.findById(id).orElse(null);
    }

    public List<Compra> getAllCompras(Pageable pageable){
        return compraRepository.findAll(pageable).getContent();
    }

    //@CacheEvict(value = "comprasByClientes", key = "#compra.cliente.id")
    public Compra saveCompras(Compra compra){
        if(compra.getQuantidade() > 100){
            throw new DomainException("Quantidade não pode ser maior que 100");
        }
        return compraRepository.save(compra);
    }

    public Boolean deleteById(Long id){
        if(compraRepository.findById(id).isPresent()){
            compraRepository.deleteById(id);
            return true;
        }

        return false;
    }

    //@Cacheable(value = "comprasByClientes", key = "#cliente.id")
    public List<Compra> findAllByCliente(Cliente cliente) {
        return compraRepository.findAllByCliente(cliente);
    }

    public List<CompraRelatorio> findAllComprasRelatorio() {
        return compraRepository.findAllComprasRelatorio();
    }
}
