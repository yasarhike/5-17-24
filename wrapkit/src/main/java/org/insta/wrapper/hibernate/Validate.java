package org.insta.wrapper.hibernate;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Set;

/**
 * <p>
 * Record representing a validator for performing object validation using Hibernate Validator.
 * </p>
 *
 * <p>
 * This record encapsulates a Hibernate {@link Validator} instance and provides a method
 * for validating objects against specified validation groups.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>{@code
 * // Get the singleton instance of Validate
 * Validate validate = Validate.getInstance();
 *
 * // Validate an object against a specific validation group
 * Set<ConstraintViolation<MyClass>> violations = validate.validate(myObject, MyValidationGroup.class);
 * }</pre>
 * </p>
 *
 * @param validator The Hibernate Validator instance used for validation.
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see Validator
 * @see ConstraintViolation
 */
public record Validate(Validator validator) {

    /**
     * <p>
     * The singleton instance of Validate.
     * </p>
     */
    private static final Validate VALIDATE = new Validate(
            Validation.byProvider(HibernateValidator.class)
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator());

    /**
     * <p>
     * Returns the singleton instance of Validate.
     * </p>
     *
     * @return The singleton instance of Validate.
     */
    public static Validate getInstance() {
        return VALIDATE;
    }

    /**
     * <p>
     * Validates an object against the specified validation groups.
     * </p>
     *
     * @param <T>    The type of the object to validate.
     * @param object The object to validate.
     * @param groups The validation groups to apply.
     * @return A set of constraint violations, or an empty set if no violations were found.
     */
    public <T> Set<ConstraintViolation<T>> validate(final T object, final Class<?> groups) {
        return validator.validate(object, groups);
    }
}