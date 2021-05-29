package br.com.zup.breno.mercadolivre.produto.pergunta;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EnviadorDeEmailFake implements EnviadorDeEmail {
    @Override
    public void enviarEmailNovaPergunta(String email, EmailResponse response) {
        System.out.println(response);
    }
}
