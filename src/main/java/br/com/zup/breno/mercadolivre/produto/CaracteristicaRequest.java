package br.com.zup.breno.mercadolivre.produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicaRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Caracteristica toModel(@NotNull @Valid Produto produto) {
        return new Caracteristica(nome, descricao, produto);
    }
}
