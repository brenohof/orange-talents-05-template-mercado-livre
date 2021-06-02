package br.com.zup.breno.mercadolivre.compra.pagamento.integracao;

import br.com.zup.breno.mercadolivre.compra.Compra;

public interface EventoCompraSucesso {
    public void processa(Compra compra);
}
