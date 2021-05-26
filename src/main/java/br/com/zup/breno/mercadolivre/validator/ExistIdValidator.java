package br.com.zup.breno.mercadolivre.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistIdValidator implements ConstraintValidator<ExistId, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> entity;

    @Override
    public void initialize(ExistId constraintAnnotation) {
        this.entity = constraintAnnotation.entity();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Query query = entityManager.createQuery("select 1 from "
                + entity.getName() + " where id = :value");

        query.setParameter("value", value);

        return !query.getResultList().isEmpty();
    }
}
