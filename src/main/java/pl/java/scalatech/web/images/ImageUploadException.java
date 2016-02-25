package pl.java.scalatech.web.images;
public class ImageUploadException extends RuntimeException {

    private static final long serialVersionUID = -8724130342036830167L;

    public ImageUploadException() {
        super();
    }

    public ImageUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ImageUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageUploadException(String message) {
        super(message);
    }

    public ImageUploadException(Throwable cause) {
        super(cause);
    }
    
}