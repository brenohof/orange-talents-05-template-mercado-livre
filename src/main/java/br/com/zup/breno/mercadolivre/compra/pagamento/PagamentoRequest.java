package br.com.zup.breno.mercadolivre.compra.pagamento;

import br.com.zup.breno.mercadolivre.compra.Compra;

public interface PagamentoRequest {

    public Pagamento toModel(Compra compra);
}
