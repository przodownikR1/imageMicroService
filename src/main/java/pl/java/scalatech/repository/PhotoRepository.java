package pl.java.scalatech.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import pl.java.scalatech.web.images.dto.Photo;

@Component
public class PhotoRepository {

    public Collection<Photo> findByKey(String key) {
        return Lists.newArrayList();
    }

    public Photo findByKeyAndId(String key, String id) {
        return new Photo() {

            @Override
            public InputStream getInputStream() throws IOException {

                return null;
            }
        };
    }
}
