package br.com.zup.breno.mercadolivre.usuario;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class isPasswordCleanValidator implements ConstraintValidator<isPasswordClean, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return false;

        return Usuario.isPasswordClean(value);
    }
}
