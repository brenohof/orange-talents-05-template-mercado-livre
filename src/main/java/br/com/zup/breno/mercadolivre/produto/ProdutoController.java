package br.com.zup.breno.mercadolivre.produto;

import br.com.zup.breno.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@Valid @RequestBody ProdutoRequest request,
                                       @AuthenticationPrincipal Usuario usuario) {
        Produto produto = request.toModel(entityManager, usuario);
        entityManager.persist(produto);
        return ResponseEntity.ok().build();
    }
}
