package br.com.zup.breno.mercadolivre.compra;

import br.com.zup.breno.mercadolivre.handler.ErrorResponse;
import br.com.zup.breno.mercadolivre.produto.Produto;
import br.com.zup.breno.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> novaCompra(@Valid @RequestBody CompraRequest request,
                                        @AuthenticationPrincipal Usuario comprador,
                                        UriComponentsBuilder uriComponentsBuilder) {
        Produto produto = entityManager.find(Produto.class, request.getProdutoId());
        Assert.state(produto!=null, "[BUG] Tentando comprar um produto não cadastrado.");

        boolean abateu = produto.abateEstoque(request.getQuantidade());
        if (!abateu) {
            return ResponseEntity.unprocessableEntity().body(
                    new ErrorResponse("quantidade", "Não foi possível abater essa quantidade"));
        }

        Compra compra = new Compra(produto, comprador, request.getQuantidade(), request.getGateway());
        entityManager.persist(compra);

        return ResponseEntity.ok(compra.urlRedirecionamento(uriComponentsBuilder));
    }
}
