package br.com.zup.breno.mercadolivre.produto.pergunta;

public interface EnviadorDeEmail {
    public void enviarEmailNovaPergunta(String email, EmailResponse response);
}
