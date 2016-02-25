package pl.java.scalatech.tools;
public class IOperationException extends RuntimeException {

    private static final long serialVersionUID = -2739398400237154735L;

    public IOperationException() {
        super();
    }

    public IOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IOperationException(String message) {
        super(message);
    }

    public IOperationException(Throwable cause) {
        super(cause);
    }
    
}
