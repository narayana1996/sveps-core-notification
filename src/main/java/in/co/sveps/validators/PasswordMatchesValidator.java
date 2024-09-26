package in.co.sveps.validators;

import in.co.sveps.entity.Employee;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Employee> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {}

    @Override
    public boolean isValid(Employee employee, ConstraintValidatorContext context) {
        boolean valid = employee.getPassword().equals(employee.getCpassword()); // Use cPassword

        if (!valid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Passwords do not match, Please retry") // Custom message
                    .addPropertyNode("cpassword") // Attach to cPassword
                    .addConstraintViolation();
        }

        return valid;
    }
}

