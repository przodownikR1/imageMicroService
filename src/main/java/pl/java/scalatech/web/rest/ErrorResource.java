package pl.java.scalatech.web.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.NativeWebRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ErrorResource extends ResourceSupport {

    private String request;

    private HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;

    private String message;

    private Class<? extends Throwable> exception;

    private List<FieldErrorResource> fieldErrors;

    public ErrorResource() {

    }

    public ErrorResource(HttpStatus status, Throwable exception, NativeWebRequest request) {

        this(status, exception.getMessage(), exception.getClass(), request);
    }

    public ErrorResource(HttpStatus status, String message, Class<? extends Throwable> exception, NativeWebRequest request) {

        this.status = status;
        this.message = message;
        this.exception = exception;

        HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
        if (servletRequest != null) {
            this.setRequest(servletRequest.getMethod() + " " + servletRequest.getRequestURI());
        }
    }

}