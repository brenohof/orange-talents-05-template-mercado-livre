package br.com.zup.breno.mercadolivre.produto.caracteristica;

import java.util.Set;

import br.com.zup.breno.mercadolivre.produto.ProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProibeCaracteristicasComMesmoNomeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ProdutoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return ;
        }

        ProdutoRequest request = (ProdutoRequest) target;
        Set<String> nomesIguais = request.buscaCaracteristicasIguais();
        if(!nomesIguais.isEmpty()) {
            errors.rejectValue("caracteristicas", null, "Olha, você tem caracteristicas iguais "+nomesIguais);
        }
    }

}
