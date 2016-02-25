package pl.java.scalatech.web.rest;

import ch.qos.logback.classic.Logger;
import lombok.Data;

@Data
public class LoggerResource {

    
    public LoggerResource(Logger logger) {
        this.name = logger.getName();
        this.level = logger.getEffectiveLevel().toString();
    }
    
    private String name;

    private String level;
}
