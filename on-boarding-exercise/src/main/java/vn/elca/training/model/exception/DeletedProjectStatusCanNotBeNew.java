package vn.elca.training.model.exception;

public class DeletedProjectStatusCanNotBeNew extends Exception {

    public DeletedProjectStatusCanNotBeNew(String message) {
        super(message);
    }

    public DeletedProjectStatusCanNotBeNew(String message, RuntimeException runtimeException) {
       super(message, runtimeException);
    }
}
