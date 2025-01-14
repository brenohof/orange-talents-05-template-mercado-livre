package br.com.zup.breno.mercadolivre.core.email;

import br.com.zup.breno.mercadolivre.compra.Compra;
import br.com.zup.breno.mercadolivre.produto.pergunta.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Component
public class Emails {

    @Autowired
    private Mailer mailer;

    public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
        mailer.send("<html>...</html>","Nova pergunta...",pergunta.getEmailUsuario(),"novapergunta@nossomercadolivre.com",pergunta.getEmailDonoProduto());
    }

    public void novaCompra(Compra novaCompra) {
        mailer.send("nova compra..." + novaCompra, "Você tem uma nova compra",
                novaCompra.getEmailComprador(),
                "compras@nossomercadolivre.com",
                novaCompra.getEmailDonoProduto());
    }


    public void falhaPagamento(Compra compra) {
        mailer.send("falha pagamento compra..." + compra, "Seu pagamento falhou",
                compra.getEmailComprador(),
                "compras@nossomercadolivre.com",
                compra.getEmailComprador());
    }

    public void sucessoPagamento(Compra compra) {
        mailer.send("Sucesso pagamento compra..." + compra, "Seu pagamento concluido com sucesso",
                compra.getEmailComprador(),
                "compras@nossomercadolivre.com",
                compra.getEmailComprador());
    }
}
