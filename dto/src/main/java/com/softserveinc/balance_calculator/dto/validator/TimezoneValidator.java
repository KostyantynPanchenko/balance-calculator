package com.softserveinc.balance_calculator.dto.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.softserveinc.balance_calculator.dto.RegisterDTO;

public class TimezoneValidator implements ConstraintValidator<ValidTimezone, RegisterDTO>{

    @Override
    public void initialize(ValidTimezone constraintAnnotation) { }

    @Override
    public boolean isValid(RegisterDTO register, ConstraintValidatorContext context) {
        String tz = register.getTimezone();
        return tz.matches("(\\+|\\-)*\\d{2}:\\d{2}") && isValidTime(tz);
    }

    private boolean isValidTime(String tz) {
        String[] parts = tz.split(":");
        Integer hours = Integer.valueOf(parts[0]);
        Integer minutes = Integer.valueOf(parts[1]);
        if (hours == -12 && minutes == 0) {
            return true;
        }
        if (hours == 12 && minutes == 0) {
            return true;
        }
        return (hours > -12 && hours < 12) && (minutes >= 0 && minutes <= 59);
    }

}
