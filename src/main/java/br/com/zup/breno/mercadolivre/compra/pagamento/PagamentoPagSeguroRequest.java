package br.com.zup.breno.mercadolivre.compra.pagamento;

import br.com.zup.breno.mercadolivre.compra.Compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagamentoPagSeguroRequest implements PagamentoRequest {
    @NotBlank
    private String pagamentoId;
    @NotNull
    private StatusPagamentoPagseguro status;

    public PagamentoPagSeguroRequest(String pagamentoId, StatusPagamentoPagseguro status) {
        this.pagamentoId = pagamentoId;
        this.status = status;
    }

    public Pagamento toModel(Compra compra) {
        return new Pagamento(status.normaliza(), pagamentoId, compra);
    }
}
