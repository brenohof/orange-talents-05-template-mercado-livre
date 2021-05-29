package br.com.zup.breno.mercadolivre.produto.opiniao;

import br.com.zup.breno.mercadolivre.produto.Produto;
import br.com.zup.breno.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "opinioes")
public class Opiniao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String titulo;
    @NotNull @Length(max=500)
    private String descricao;
    @NotNull @Min(1) @Max(5)
    private Integer nota;
    @NotNull @ManyToOne
    private Usuario usuario;
    @NotNull @ManyToOne
    private Produto produto;

    @Deprecated
    public Opiniao() {
    }

    public Opiniao(@NotNull String titulo, @NotNull String descricao,
                   @NotNull @Min(1) @Max(5) Integer nota, @NotNull Usuario usuario,
                   @NotNull Produto produto) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
        this.usuario = usuario;
        this.produto = produto;
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
