package br.com.zup.breno.mercadolivre.produto;

import br.com.zup.breno.mercadolivre.categoria.Categoria;
import br.com.zup.breno.mercadolivre.usuario.Usuario;
import br.com.zup.breno.mercadolivre.validator.ExistId;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRequest {
    @NotBlank
    private String nome;
    @NotNull @Positive
    private BigDecimal valor;
    @NotNull @Min(0)
    private Long quantidade;
    @NotBlank @Size(max = 1000)
    private String descricao;
    @NotNull @ExistId(entity = Categoria.class)
    private Long categoriaId;
    @Size(min = 3) @Valid
    private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();

    public ProdutoRequest(@NotBlank  String nome, @Positive BigDecimal valor,
                          @Min(0) Long quantidade, @Size(max = 1000) String descricao,
                          Long categoriaId, @Size(min=3) List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.caracteristicas.addAll(caracteristicas);
    }

    public Produto toModel(EntityManager entityManager, Usuario usuario) {
        Categoria categoria = entityManager.find(Categoria.class, categoriaId);

        Assert.state(categoria!=null, "[BUG] Essa categoria é nula.");
        Assert.state(usuario!=null, "[BUG] Essa usuário é nulo.");

        return new Produto(nome, descricao, valor, caracteristicas, quantidade, categoria, usuario);
    }
}
