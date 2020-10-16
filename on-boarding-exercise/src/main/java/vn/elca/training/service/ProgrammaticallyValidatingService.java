package vn.elca.training.service;

import org.springframework.stereotype.Service;

import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Project;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

@Service
public class ProgrammaticallyValidatingService {
    private Validator validator;

    ProgrammaticallyValidatingService(Validator validator) {
        this.validator = validator;
    }

    public void validateInputWithInjectedValidator(ProjectDto input) {
        Set<ConstraintViolation<ProjectDto>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public void validateInput(ProjectDto input) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ProjectDto>> violations = validator.validate(input);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
