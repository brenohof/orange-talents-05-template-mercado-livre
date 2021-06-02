package br.com.zup.breno.mercadolivre.compra.pagamento;

public enum StatusPagamentoPagseguro {
    SUCESSO, ERRO;

    StatusPagamento normaliza() {
        if (this.equals(SUCESSO)) {
            return StatusPagamento.SUCESSO;
        }

        return StatusPagamento.FALHA;
    }
}
