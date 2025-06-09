package com.yourmenu.yourmenu_api.shared.notations.nullOrNotBlank;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = NullOrNotBlankValidator.class)
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface NullOrNotBlank {
    String message() default "O campo não pode estar em branco se for preenchido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

