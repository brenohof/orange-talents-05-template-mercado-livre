package br.com.zup.breno.mercadolivre.usuario;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = isPasswordCleanValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface isPasswordClean {
    String message() default
            "{br.com.zup.beanvalidation.ispasswordclean}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
