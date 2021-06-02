package br.com.zup.breno.mercadolivre.compra.pagamento.integracao;

import br.com.zup.breno.mercadolivre.compra.Compra;
import br.com.zup.breno.mercadolivre.compra.pagamento.integracao.EventoCompraSucesso;
import br.com.zup.breno.mercadolivre.core.email.Emails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class EventosNovaCompra {

    @Autowired
    private Emails emails;
    @Autowired
    private Set<EventoCompraSucesso> eventoCompraSucesso;
    public void processa(Compra compra) {
        if (compra.foiConcluida()) {
            eventoCompraSucesso.forEach(evento -> evento.processa(compra));
            emails.sucessoPagamento(compra);
        } else {
            emails.falhaPagamento(compra);
        }
    }
}
