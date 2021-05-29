package br.com.zup.breno.mercadolivre.produto.pergunta;

public class EmailResponse {
    private String pergunta;
    private String usuario;
    private String produto;

    public EmailResponse(Pergunta pergunta) {
        this.pergunta = pergunta.getTitulo();
        this.usuario = pergunta.getEmailUsuario();
        this.produto = pergunta.getNomeProduto();
    }

    @Override
    public String toString() {
        return "Nova Pergunta: \n"+
                "pergunta=" + pergunta + '\n' +
                "usuario=" + usuario + '\n' +
                "produto=" + produto + '\n';
    }

}
