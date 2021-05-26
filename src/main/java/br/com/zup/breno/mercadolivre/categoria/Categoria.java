package br.com.zup.breno.mercadolivre.categoria;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Column(unique = true)
    private String nome;
    @ManyToOne
    private Categoria categoriaMae;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nome) {
        Assert.state(nome != null, "não é possível criar uma categoria sem nome.");

        this.nome = nome;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        Assert.state(categoriaMae != null, "não é possível associar uma categoria mãe nula.");

        this.categoriaMae = categoriaMae;
    }
}
