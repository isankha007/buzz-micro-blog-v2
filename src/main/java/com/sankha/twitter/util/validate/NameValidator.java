package com.sankha.twitter.util.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class NameValidator implements ConstraintValidator <NameValid,String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value.isBlank()){
            return false;
        }else{
            return true;
        }
    }
}
