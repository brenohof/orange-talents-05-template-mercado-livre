package br.com.zup.breno.mercadolivre.produto.imagem;

import br.com.zup.breno.mercadolivre.produto.Produto;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;

@Entity
@Table(name="imagens")
public class Image {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @URL
    private String link;
    @ManyToOne @NotNull
    private Produto produto;

    @Deprecated
    public Image() {}

    public Image(@URL @NotNull String link, @NotNull @Valid Produto produto) {
        this.link = link;
        this.produto = produto;
    }
}
