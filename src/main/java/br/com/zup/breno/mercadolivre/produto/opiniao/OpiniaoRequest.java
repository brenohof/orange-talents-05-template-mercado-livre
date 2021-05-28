package br.com.zup.breno.mercadolivre.produto.opiniao;

import br.com.zup.breno.mercadolivre.produto.Produto;
import br.com.zup.breno.mercadolivre.usuario.Usuario;
import br.com.zup.breno.mercadolivre.validator.ExistId;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpiniaoRequest {
    @Min(1) @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank @Length(max = 500)
    private String descricao;
    @NotNull @ExistId(entity = Produto.class)
    private Long produtoId;

    public OpiniaoRequest(Integer nota, String titulo, String descricao, Long produtoId) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produtoId = produtoId;
    }

    public Opiniao toModel(EntityManager entityManager, Usuario usuario) {
        Produto produto = entityManager.find(Produto.class, produtoId);

        Assert.state(produto != null, "Produto não deve ser nulo!");
        Assert.state(usuario != null, "Usuário não deve ser nulo!");

        return new Opiniao(titulo, descricao, nota, usuario, produto);
    }
}
