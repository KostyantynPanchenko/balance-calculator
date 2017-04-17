package balance.calculator.dto.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimezoneValidator.class)
public @interface ValidTimezone {
    String message() default "Incorrect timezone format. Use example format -08:00";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
