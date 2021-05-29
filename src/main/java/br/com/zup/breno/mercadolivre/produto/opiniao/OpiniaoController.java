package br.com.zup.breno.mercadolivre.produto.opiniao;

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
public class OpiniaoController {

    @Autowired
    private EntityManager entityManager;

    @PostMapping("/produtos/opinioes")
    @Transactional
    public ResponseEntity<?> adicionar(@Valid @RequestBody OpiniaoRequest request,
                                              @AuthenticationPrincipal Usuario usuario) {
        Opiniao opiniao =  request.toModel(entityManager, usuario);
        entityManager.persist(opiniao);
        return ResponseEntity.ok().build();
    }
}
