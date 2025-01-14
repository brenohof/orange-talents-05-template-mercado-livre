package br.com.zup.breno.mercadolivre.produto;

import br.com.zup.breno.mercadolivre.produto.caracteristica.ProibeCaracteristicasComMesmoNomeValidator;
import br.com.zup.breno.mercadolivre.produto.imagem.ImageRequest;
import br.com.zup.breno.mercadolivre.produto.imagem.Uploader;
import br.com.zup.breno.mercadolivre.produto.opiniao.OpiniaoResponse;
import br.com.zup.breno.mercadolivre.produto.pergunta.Pergunta;
import br.com.zup.breno.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private Uploader uploaderFake;

    @InitBinder(value = "produtoRequest")
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeCaracteristicasComMesmoNomeValidator());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@Valid @RequestBody ProdutoRequest request,
                                       @AuthenticationPrincipal Usuario usuario) {
        Produto produto = request.toModel(entityManager, usuario);
        entityManager.persist(produto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionarImagem(@PathVariable Long id,
                                             @Valid ImageRequest request,
                                             @AuthenticationPrincipal Usuario usuario) {
        Produto produto = entityManager.find(Produto.class, id);
        if (produto == null)
            return ResponseEntity.notFound().build();

        if (!produto.getUsuario().equals(usuario))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Set<String> links = uploaderFake.envia(request.getImagens());
        produto.associaImagens(links);

        entityManager.merge(produto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        Produto produto = entityManager.find(Produto.class, id);
        if (produto == null)
            return ResponseEntity.notFound().build();

        List<OpiniaoResponse> opinioes = OpiniaoResponse.toList(id, entityManager);
        List<String> perguntas = Pergunta.toList(id, entityManager);
        ProdutoResponse produtoResponse = new ProdutoResponse(produto, opinioes, perguntas);

        return ResponseEntity.ok(produtoResponse);
    }
}
