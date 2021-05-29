package br.com.zup.breno.mercadolivre.produto.pergunta;

import br.com.zup.breno.mercadolivre.produto.Produto;
import br.com.zup.breno.mercadolivre.produto.opiniao.Opiniao;
import br.com.zup.breno.mercadolivre.produto.opiniao.OpiniaoResponse;
import br.com.zup.breno.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "perguntas")
public class Pergunta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String titulo;
    @NotNull @ManyToOne
    private Usuario usuario;
    @NotNull @ManyToOne
    private Produto produto;
    private LocalDateTime instanteDeCricao = LocalDateTime.now();

    @Deprecated
    public Pergunta() {
    }

    /**
     * @param titulo não pode ser nulo.
     * @param usuario não pode ser nulo.
     * @param produto não pode ser nulo.
     */
    public Pergunta(String titulo, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    public static List<String> toList(Long produtoId, EntityManager entityManager) {
        Query query = entityManager.createQuery("select p from Pergunta p where produto_id = :id");
        query.setParameter("id", produtoId);
        List<Pergunta> perguntas = query.getResultList();

        if (perguntas == null) {
            return null;
        }

        return perguntas.stream()
                .map(pergunta -> pergunta.getTitulo())
                .collect(Collectors.toList());
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEmailUsuario() {
        return usuario.getEmail();
    }

    public String getNomeProduto() {
        return produto.getNome();
    }

    public String getEmailDonoProduto() {
        return produto.getUsuarioEmail();
    }


}
