package br.com.zup.breno.mercadolivre.compra.pagamento.integracao;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FakeController {

    @PostMapping("/notas-fiscais")
    public void notasFiscais(@RequestBody Map<String, Object> request) {
        System.out.println("Nova nota fiscal " + request.toString());
    }

    @PostMapping("/ranking")
    public void ranking(@RequestBody Map<String, Object> request) {
        System.out.println(request.toString());
    }
}
