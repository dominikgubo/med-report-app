package org.acme.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.function.Predicate;

public final class BloodTypeValidator implements ConstraintValidator<ValidBloodType, String> {
    List<String> charBloodGroups = List.of("A", "B", "O", "AB");
    Predicate<String> ABCondition = s -> "AB".equals(s.substring(0,2)) && s.length() == 3 && checkOperator(s);
    Predicate<String> shortGroupCondition = s -> charBloodGroups.contains(s.charAt(0)) && s.length() == 2 && checkOperator(s);


    @Override
    public boolean isValid(String bloodType, ConstraintValidatorContext constraintValidatorContext) {
        // As it's not mandatory
        if(bloodType == null) return true;
        if(ABCondition.test(bloodType)) return true;
        return (shortGroupCondition.test(bloodType));
    }

    public boolean checkOperator(String bloodType){
        return bloodType.endsWith("+") || bloodType.endsWith("-");
    }
}
