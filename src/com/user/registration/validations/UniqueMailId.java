package com.user.registration.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = UniqueUserMailIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueMailId {

		// define default error message
		public String message() default "You have already registered one account with this mail-id.";
		
		// define default groups
		public Class<?>[] groups() default {};
		
		// define default payloads
		public Class<? extends Payload>[] payload() default {};
		
}
