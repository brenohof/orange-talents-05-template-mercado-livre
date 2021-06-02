package br.com.zup.breno.mercadolivre.compra.pagamento;

import br.com.zup.breno.mercadolivre.compra.Compra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagamentoPaypalRequest implements PagamentoRequest{
    @NotBlank
    private String pagamentoId;
    @NotNull @Min(0) @Max(1)
    private Integer status;

    public PagamentoPaypalRequest(String pagamentoId, Integer status) {
        this.pagamentoId = pagamentoId;
        this.status = status;
    }

    public Pagamento toModel(Compra compra) {
        StatusPagamento statusPagamento = status == 0 ? StatusPagamento.FALHA : StatusPagamento.SUCESSO;
        return new Pagamento(statusPagamento, pagamentoId, compra);
    }

}
