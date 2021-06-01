package br.com.zup.breno.mercadolivre.compra;

import br.com.zup.breno.mercadolivre.produto.Produto;
import br.com.zup.breno.mercadolivre.usuario.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "compras")
public class Compra {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @NotNull @Valid
    private Produto produto;
    @ManyToOne @NotNull @Valid
    private Usuario comprador;
    @NotNull @Positive
    private Long quantidade;
    @NotNull
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private StatusCompra status = StatusCompra.INICIADA;
    @Enumerated(EnumType.STRING)
    private GatewayCompra gateway;

    @Deprecated
    public Compra() {}

    public Compra(@NotNull @Valid Produto produto, @NotNull @Valid Usuario comprador, Long quantidade, GatewayCompra gateway) {
        this.produto = produto;
        this.comprador = comprador;
        this.quantidade = quantidade;
        this.valor = produto.getValor();
        this.gateway = gateway;
    }

    public Long getId() {
        return id;
    }

    public String urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
        return this.gateway.criaUrlRetorno(this, uriComponentsBuilder);
    }
}
