package br.com.zup.breno.mercadolivre.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@Valid @RequestBody CategoriaRequest request) {
        Categoria categoria = request.toModel(entityManager);
        entityManager.persist(categoria);
        return ResponseEntity.ok().build();
    }
}
