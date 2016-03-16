package pl.java.scalatech.web.images.dto;

import java.io.IOException;
import java.io.InputStream;
@FunctionalInterface
public interface Photo {
    InputStream getInputStream() throws IOException;

}