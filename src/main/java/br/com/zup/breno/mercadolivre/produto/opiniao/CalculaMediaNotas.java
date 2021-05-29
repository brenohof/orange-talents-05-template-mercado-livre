package br.com.zup.breno.mercadolivre.produto.opiniao;

import java.util.List;
import java.util.stream.Collectors;

public class CalculaMediaNotas {

    public static Double calcular(List<OpiniaoResponse> opinioes) {
        List<Integer> notas = opinioes.stream().map(opiniao -> opiniao.getNota()).collect(Collectors.toList());
        Integer soma = notas.stream().reduce(0, (subtotal, nota) -> subtotal + nota);
        Double media = soma.doubleValue() / opinioes.size();
        return media;
    }
}
