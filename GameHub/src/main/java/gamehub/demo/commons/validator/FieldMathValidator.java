package gamehub.demo.commons.validator;

import org.hibernate.type.ObjectType;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMathValidator implements ConstraintValidator<FiledMatch,Object> {

    private String firstField;
    private String secondField;
    private String message;
    @Override
    public void initialize(FiledMatch constraintAnnotation) {
        this.firstField=constraintAnnotation.firstFiled();
        this.secondField=constraintAnnotation.secondField();
        this.message=constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        BeanWrapper wrapper= PropertyAccessorFactory.forBeanPropertyAccess(o);
        Object first=wrapper.getPropertyValue(firstField);
        Object second=wrapper.getPropertyValue(secondField);

        boolean valid=(first==null&&second==null)
                ||(first!=null && first.equals(second));
        if(!valid){
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return valid;
    }
}
