package vn.elca.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.elca.training.model.dto.ValidationErrorResponse;
import vn.elca.training.model.dto.Violation;
import vn.elca.training.model.exception.ForeignKeyNotFoundException;
import vn.elca.training.web.ApplicationController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


@RestControllerAdvice(basePackageClasses = { ApplicationController.class})
public class ControllerAdvisor {

    /**
     * Exception not declare will handle here
     */
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorMessage handleAllException(Exception ex, WebRequest request) {
//
//        return new ErrorMessage(ex.getCause(), ex.getLocalizedMessage());
//    }
//
//    @ExceptionHandler(ConcurrentUpdateException.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorMessage handleConcurrentUpdateException(ConcurrentUpdateException ex, WebRequest request) {
//       ex.printStackTrace();
//        return new ErrorMessage(ex.getCause(), ex.getLocalizedMessage());
//    }
    // for @Valid
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleConstrainValidation(ConstraintViolationException ex) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            error.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }

    // for @Validated on class
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(
                    new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }


    @ExceptionHandler(ForeignKeyNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleDatabaseSavingException(
            ForeignKeyNotFoundException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getCause(),ex.getMessage()), HttpStatus.NOT_FOUND);
    }

}
