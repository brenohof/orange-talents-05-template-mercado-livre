package br.com.zup.breno.mercadolivre.compra.pagamento.integracao;

import br.com.zup.breno.mercadolivre.compra.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra) {
        if (compra.foiConcluida()) {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getIdComprador());

            restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
        }
    }
}
