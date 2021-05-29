package br.com.zup.breno.mercadolivre.produto;

import br.com.zup.breno.mercadolivre.produto.caracteristica.CaracteristicaResponse;
import br.com.zup.breno.mercadolivre.produto.opiniao.CalculaMediaNotas;
import br.com.zup.breno.mercadolivre.produto.opiniao.OpiniaoResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoResponse {
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Long quantidade;
    private List<CaracteristicaResponse> caracteristicas = new ArrayList<>();
    private List<String> imagens = new ArrayList<>();
    private List<String> perguntas = new ArrayList<>();
    private List<OpiniaoResponse> opinioes = new ArrayList<>();
    private Double mediaNotas;

    public ProdutoResponse(Produto produto,
                           List<OpiniaoResponse> opinioes,
                           List<String> perguntas) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        this.caracteristicas.addAll(listaDeCaracteristicas(produto));
        this.imagens.addAll(listaDeImagens(produto));
        this.opinioes.addAll(opinioes);
        this.perguntas.addAll(perguntas);
        this.mediaNotas = CalculaMediaNotas.calcular(opinioes);
    }

    private Collection<String> listaDeImagens(Produto produto) {
        return produto.getImagens().stream()
                .map(image -> image.getLink())
                .collect(Collectors.toList());
    }

    private Collection<CaracteristicaResponse> listaDeCaracteristicas(Produto produto) {
        return CaracteristicaResponse.toList(produto.getCaracteristicas());
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public List<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public List<String> getPerguntas() {
        return perguntas;
    }

    public List<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }
}
