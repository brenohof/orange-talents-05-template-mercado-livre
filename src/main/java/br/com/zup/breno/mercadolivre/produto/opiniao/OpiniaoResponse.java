package br.com.zup.breno.mercadolivre.produto.opiniao;

import br.com.zup.breno.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zup.breno.mercadolivre.produto.caracteristica.CaracteristicaResponse;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

public class OpiniaoResponse {
    private String titulo;
    private String descricao;
    private Integer nota;

    public OpiniaoResponse(Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.nota = opiniao.getNota();
    }

    public static List<OpiniaoResponse> toList(Long produtoId, EntityManager entityManager) {
        Query query = entityManager.createQuery("select o from Opiniao o where produto_id = :id");
        query.setParameter("id", produtoId);
        List<Opiniao> opinioes = query.getResultList();

        if (opinioes == null) {
            return null;
        }

        return opinioes.stream()
                .map(opiniao -> new OpiniaoResponse(opiniao))
                .collect(Collectors.toList());
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }
}
