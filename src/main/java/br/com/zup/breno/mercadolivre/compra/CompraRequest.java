package br.com.zup.breno.mercadolivre.compra;

import br.com.zup.breno.mercadolivre.produto.Produto;
import br.com.zup.breno.mercadolivre.usuario.Usuario;
import br.com.zup.breno.mercadolivre.validator.ExistId;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {
    @Positive @NotNull
    private Long quantidade;
    @NotNull @ExistId(entity = Produto.class)
    private Long produtoId;
    @NotNull
    private GatewayCompra gateway;

    public CompraRequest(Long quantidade, Long produtoId, GatewayCompra gateway) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.gateway = gateway;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public GatewayCompra getGateway() {
        return gateway;
    }
}
