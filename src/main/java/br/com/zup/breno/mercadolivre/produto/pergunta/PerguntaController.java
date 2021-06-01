package br.com.zup.breno.mercadolivre.produto.pergunta;

import br.com.zup.breno.mercadolivre.core.email.Emails;
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
    private Emails emails;

    @PostMapping("/produtos/perguntas")
    @Transactional
    public ResponseEntity<?> adicionar(@Valid @RequestBody PerguntaRequest request,
                                              @AuthenticationPrincipal Usuario usuario) {
        Pergunta pergunta =  request.toModel(entityManager, usuario);
        entityManager.persist(pergunta);
        emails.novaPergunta(pergunta);

        return ResponseEntity.ok().build();
    }
}
