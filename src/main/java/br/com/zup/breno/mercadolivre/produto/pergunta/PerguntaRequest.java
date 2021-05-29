package br.com.zup.breno.mercadolivre.produto.pergunta;

import br.com.zup.breno.mercadolivre.produto.Produto;
import br.com.zup.breno.mercadolivre.produto.opiniao.Opiniao;
import br.com.zup.breno.mercadolivre.usuario.Usuario;
import br.com.zup.breno.mercadolivre.validator.ExistId;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PerguntaRequest {
    @NotBlank
    private String titulo;
    @NotNull @ExistId(entity = Produto.class)
    private Long produtoId;

    public PerguntaRequest(String titulo, Long produtoId) {
        this.titulo = titulo;
        this.produtoId = produtoId;
    }

    public Pergunta toModel(EntityManager entityManager, Usuario usuario) {
        Produto produto = entityManager.find(Produto.class, produtoId);

        Assert.state(produto != null, "Produto não deve ser nulo!");
        Assert.state(usuario != null, "Usuário não deve ser nulo!");

        return new Pergunta(titulo, usuario, produto);
    }
}
