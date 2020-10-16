package vn.elca.training.model.exception;

public class ConcurrentUpdateException extends Exception {

    public ConcurrentUpdateException(String message) {
        super(message);
    }

    public ConcurrentUpdateException(String message, Throwable runtimeException) {
        super(message, runtimeException);
    }
}
