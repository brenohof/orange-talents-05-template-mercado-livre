package br.com.zup.breno.mercadolivre.produto.pergunta;

import br.com.zup.breno.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
public class PerguntaController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EnviadorDeEmail enviadorDeEmailFake;

    @PostMapping("/produtos/perguntas")
    @Transactional
    public ResponseEntity<?> adicionarOpiniao(@Valid @RequestBody PerguntaRequest request,
                                              @AuthenticationPrincipal Usuario usuario) {
        Pergunta pergunta =  request.toModel(entityManager, usuario);
        EmailResponse emailResponse = new EmailResponse(pergunta);
        entityManager.persist(pergunta);
        enviadorDeEmailFake.enviarEmailNovaPergunta(pergunta.getEmailDonoProduto(), emailResponse);
        return ResponseEntity.ok().build();
    }
}
