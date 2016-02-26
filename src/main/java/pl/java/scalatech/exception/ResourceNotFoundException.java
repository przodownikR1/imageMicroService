package pl.java.scalatech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Sławomir Borowiec 
 * Module name : imageMicroService
 * Creating time :  26 lut 2016 15:29:53
 
 */
@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 402250074600556489L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);   
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);      
    }

    
    
}
