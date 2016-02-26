package pl.java.scalatech.tools;
public class IOOperationException extends RuntimeException {

    private static final long serialVersionUID = -2739398400237154735L;

    public IOOperationException() {
        super();
    }

    public IOOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IOOperationException(String message) {
        super(message);
    }

    public IOOperationException(Throwable cause) {
        super(cause);
    }
    
}
