package br.com.zup.breno.mercadolivre.autenticacao;

public class TokenResponse {
    private String token;
    private String tipo;

    public TokenResponse(String token, String tipo) {
        this.tipo = tipo;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
