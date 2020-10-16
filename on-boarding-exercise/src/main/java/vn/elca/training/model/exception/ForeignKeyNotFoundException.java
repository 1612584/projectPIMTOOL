package vn.elca.training.model.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ForeignKeyNotFoundException extends RuntimeException {

    public ForeignKeyNotFoundException(String message) {
        super(message);
    }

    public ForeignKeyNotFoundException(String message, RuntimeException runtimeException) {
        super(message, runtimeException);
    }
}
