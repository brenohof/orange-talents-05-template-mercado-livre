package br.com.zup.breno.mercadolivre.compra.pagamento.integracao;

import br.com.zup.breno.mercadolivre.compra.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements EventoCompraSucesso {
    public void processa(Compra compra) {
        if (compra.foiConcluida()) {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> request = Map.of("idCompra", compra.getId(), "idDono", compra.getIdDono());

            restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);
        }
    }
}
