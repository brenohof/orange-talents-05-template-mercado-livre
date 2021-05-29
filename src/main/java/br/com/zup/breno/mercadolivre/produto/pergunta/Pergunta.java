package br.com.zup.breno.mercadolivre.produto.pergunta;

import br.com.zup.breno.mercadolivre.produto.Produto;
import br.com.zup.breno.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
