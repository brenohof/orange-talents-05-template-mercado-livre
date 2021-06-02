package br.com.zup.breno.mercadolivre.compra.pagamento;

import br.com.zup.breno.mercadolivre.compra.Compra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @Valid @NotNull
    private Compra compra;
    @NotNull @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    private @NotBlank String gatewayPagamentoId;
    @NotNull
    private LocalDateTime instante;

    @Deprecated
    public Pagamento() {}

    public Pagamento(StatusPagamento status, String pagamentoId, Compra compra) {
        this.status = status;
        this.gatewayPagamentoId = pagamentoId;
        this.instante = LocalDateTime.now();
        this.compra = compra;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusPagamento.SUCESSO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id) && Objects.equals(compra, pagamento.compra) && status == pagamento.status && Objects.equals(gatewayPagamentoId, pagamento.gatewayPagamentoId) && Objects.equals(instante, pagamento.instante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, compra, status, gatewayPagamentoId, instante);
    }
}
