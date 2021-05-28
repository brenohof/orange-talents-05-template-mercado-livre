package br.com.zup.breno.mercadolivre.usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioRequest request) {
        Usuario usuario = request.toModel();
        entityManager.persist(usuario);
        return ResponseEntity.ok().build();
    }
}
