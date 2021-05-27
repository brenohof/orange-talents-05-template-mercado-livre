package br.com.zup.breno.mercadolivre.categoria;

import br.com.zup.breno.mercadolivre.validator.ExistId;
import br.com.zup.breno.mercadolivre.validator.UniqueValue;
import org.springframework.util.Assert;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class CategoriaRequest {
    @NotBlank @UniqueValue(entity = Categoria.class, field = "nome")
    private String nome;
    @ExistId(entity = Categoria.class)
    private Long categoriaMaeId;

    public CategoriaRequest(@NotBlank String nome, Long categoriaMaeId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaMaeId;
    }


    public Categoria toModel(EntityManager entityManager) {
        Categoria novaCategoria = new Categoria(nome);

        if (categoriaMaeId != null) {
            Categoria categoriaMae = entityManager.find(Categoria.class, categoriaMaeId);

            Assert.state(categoriaMae != null, "você está tentando criar uma nova categoria com uma categoria mãe que não existe "+categoriaMaeId);

            novaCategoria.setCategoriaMae(categoriaMae);
        }

        return novaCategoria;
    }
}
