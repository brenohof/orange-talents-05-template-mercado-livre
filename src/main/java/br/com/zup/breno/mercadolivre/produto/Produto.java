package br.com.zup.breno.mercadolivre.produto;

import br.com.zup.breno.mercadolivre.categoria.Categoria;
import br.com.zup.breno.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @NotNull
    private String nome;
    @NotNull @NotBlank @Length(max=1000)
    private String descricao;
    @Positive @NotNull
    private BigDecimal valor;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<Caracteristica> caracteristicas = new HashSet<>();
    @Min(0) @NotNull
    private Long quantidade;
    @ManyToOne @NotNull @Valid
    private Categoria categoria;
    @NotNull
    private LocalDateTime instanteDeCadastro = LocalDateTime.now();
    @ManyToOne @NotNull @Valid
    private Usuario usuario;

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, String descricao, BigDecimal valor,
                   Collection<CaracteristicaRequest> caracteristicas,
                   Long quantidade, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.caracteristicas.addAll(caracteristicas.stream()
                .map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet()));
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.usuario = usuario;
    }
}
