package br.com.gabrielrps.compras.input;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompraRelatorio {

    private Long compraId;
    private String cliente;
    private String produto;
    private Integer quantidade;

}
