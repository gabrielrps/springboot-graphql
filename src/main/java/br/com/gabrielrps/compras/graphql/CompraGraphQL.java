package br.com.gabrielrps.compras.graphql;

import br.com.gabrielrps.compras.input.CompraInput;
import br.com.gabrielrps.compras.input.CompraRelatorio;
import br.com.gabrielrps.compras.model.Compra;
import br.com.gabrielrps.compras.service.ClienteService;
import br.com.gabrielrps.compras.service.CompraService;
import br.com.gabrielrps.compras.service.ProdutoService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CompraGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private CompraService compraService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    public Compra compra(Long id){
        return compraService.findById(id);
    }

    public List<Compra> compras(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("quantidade").descending());
        return compraService.getAllCompras(pageable);
    }

    public Compra saveCompra(CompraInput compraInput){
        ModelMapper modelMapper = new ModelMapper();
        Compra compra = modelMapper.map(compraInput, Compra.class);

        compra.setData(new Date());
        compra.setCliente(clienteService.findById(compraInput.getClienteId()));
        compra.setProduto(produtoService.findById(compraInput.getProdutoId()));

        return compraService.saveCompras(compra);
    }

    public Boolean deleteCompra(Long id){
        if(compraService.findById(id) != null){
            compraService.deleteById(id);
            return true;
        }
        return false;
    }

    public List<CompraRelatorio> comprasRelatorio(){
        return compraService.findAllComprasRelatorio();
    }

}
