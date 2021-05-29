package br.com.zup.breno.mercadolivre.produto.caracteristica;

import java.util.List;
import java.util.stream.Collectors;

public class CaracteristicaResponse {
    private String nome;
    private String descricao;

    public CaracteristicaResponse(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public static List<CaracteristicaResponse> toList(List<Caracteristica> caracteristicas) {
        return caracteristicas.stream()
                .map(caracteristica -> new CaracteristicaResponse(caracteristica))
                .collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
