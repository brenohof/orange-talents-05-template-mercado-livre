package br.com.zup.breno.mercadolivre.produto;

import br.com.zup.breno.mercadolivre.categoria.Categoria;
import br.com.zup.breno.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zup.breno.mercadolivre.produto.imagem.Image;
import br.com.zup.breno.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @NotNull
    @Column(unique = true)
    private String nome;
    @NotNull
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @Positive
    @NotNull
    private BigDecimal valor;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<Caracteristica> caracteristicas = new HashSet<>();
    @Min(0)
    @NotNull
    private Long quantidade;
    @ManyToOne
    @NotNull
    @Valid
    private Categoria categoria;
    @NotNull
    private LocalDateTime instanteDeCadastro = LocalDateTime.now();
    @ManyToOne
    @NotNull
    @Valid
    private Usuario usuario;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Image> imagens = new HashSet<>();

    @Deprecated
    public Produto() {
    }

    public Produto(ProdutoRequest produtoRequest, Categoria categoria, Usuario usuario) {
        this.nome = produtoRequest.getNome();
        this.descricao = produtoRequest.getDescricao();
        this.valor = produtoRequest.getValor();
        this.caracteristicas.addAll(produtoRequest.getCaracteristicas().stream()
                .map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet()));
        this.quantidade = produtoRequest.getQuantidade();
        this.categoria = categoria;
        this.usuario = usuario;

        Assert.isTrue(this.caracteristicas.size() >= 3,
                "Todo produto precisa ter no mínimo 3 ou mais características");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void associaImagens(Set<String> links) {
        Set<Image> imagens = links.stream()
                .map(link -> new Image(link, this))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

    public String getNome() {
        return nome;
    }

    public String getUsuarioEmail() {
        return usuario.getEmail();
    }
}
