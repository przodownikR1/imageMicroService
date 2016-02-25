package pl.java.scalatech.web.images.dto;

import java.io.IOException;
import java.io.InputStream;

public interface Photo {


    public InputStream getInputStream() throws IOException;

}