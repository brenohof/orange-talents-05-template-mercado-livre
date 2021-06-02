package br.com.zup.breno.mercadolivre.compra;

import br.com.zup.breno.mercadolivre.compra.pagamento.Pagamento;
import br.com.zup.breno.mercadolivre.compra.pagamento.PagamentoPagSeguroRequest;
import br.com.zup.breno.mercadolivre.compra.pagamento.PagamentoRequest;
import br.com.zup.breno.mercadolivre.compra.pagamento.StatusPagamento;
import br.com.zup.breno.mercadolivre.produto.Produto;
import br.com.zup.breno.mercadolivre.usuario.Usuario;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "compra", cascade =  CascadeType.MERGE)
    private Set<Pagamento> pagamentos = new HashSet<>();

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

    public String getEmailComprador() {
        return this.comprador.getEmail();
    }

    public String getEmailDonoProduto() {
        return this.produto.getUsuarioEmail();
    }

    public boolean adicionarPagamento(@Valid PagamentoRequest request) {
        Pagamento novoPagamento = request.toModel(this);

        if (this.status.equals(StatusCompra.CONCLUIDA)) {
            return false;
        }

        Assert.isTrue(!this.pagamentos.contains(novoPagamento), "[BUG] Já existe esse pagamento.");

        if (novoPagamento.getStatus().equals(StatusPagamento.SUCESSO)) {
            this.status = StatusCompra.CONCLUIDA;
        }

        this.pagamentos.add(novoPagamento);
        return true;
    }

    public boolean foiConcluida() {
        return this.status.equals(StatusCompra.CONCLUIDA);
    }
}
