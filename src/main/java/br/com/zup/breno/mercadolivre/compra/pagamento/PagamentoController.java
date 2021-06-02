package br.com.zup.breno.mercadolivre.compra.pagamento;

import br.com.zup.breno.mercadolivre.compra.Compra;
import br.com.zup.breno.mercadolivre.compra.pagamento.integracao.EventosNovaCompra;
import br.com.zup.breno.mercadolivre.handler.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
public class PagamentoController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EventosNovaCompra eventosNovaCompra;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public ResponseEntity<?> pagamentoPagseguro(@PathVariable Long id,
                                                @Valid @RequestBody PagamentoPagSeguroRequest request) {
        return processa(id, request);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<?> pagamentoPaypal(@PathVariable Long id,
                                             @Valid @RequestBody PagamentoPaypalRequest request) {
        return processa(id, request);
    }

    private ResponseEntity<?> processa(Long id, PagamentoRequest request) {
        Compra compra = entityManager.find(Compra.class, id);
        if (compra == null)
            return ResponseEntity.notFound().build();

        boolean adicionado = compra.adicionarPagamento(request);
        if (!adicionado) {
            return ResponseEntity.unprocessableEntity().body(
                    new ErrorResponse("compra", "Está compra já foi concluida."));
        }

        eventosNovaCompra.processa(compra);

        entityManager.merge(compra);


        return ResponseEntity.ok().build();
    }
}
