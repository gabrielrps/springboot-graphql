package br.com.gabrielrps.compras.repository;

import br.com.gabrielrps.compras.input.CompraRelatorio;
import br.com.gabrielrps.compras.model.Cliente;
import br.com.gabrielrps.compras.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query("select c from Compra c where c.cliente = :cliente")
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    List<Compra> findAllByCliente(@Param("cliente") Cliente cliente);

    @Query("select new br.com.gabrielrps.compras.input.CompraRelatorio(c.id, cli.nome, p.nome, c.quantidade) from Compra c inner join c.cliente cli inner join c.produto p ")
    List<CompraRelatorio> findAllComprasRelatorio();
}
