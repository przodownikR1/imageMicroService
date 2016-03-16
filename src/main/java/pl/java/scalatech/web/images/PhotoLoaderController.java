package pl.java.scalatech.web.images;

import static java.util.Arrays.stream;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.BootRest;
import pl.java.scalatech.repository.PhotoRepository;
import pl.java.scalatech.tools.FileOperations;
import pl.java.scalatech.web.hateoas.PhotoResource;
import pl.java.scalatech.web.images.dto.Photo;

@RestController
@RequestMapping("/photoLoader")
@Slf4j
public class PhotoLoaderController {
    @Autowired
    private PhotoRepository photoRepository;

    @RequestMapping(value = "/{id}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<PhotoResource> readPhotoResource(@PathVariable String key, @PathVariable String id) {
        Photo photo = this.photoRepository.findByKeyAndId(key, id);
        // Photo photo = () -> this.fs.getResource(dogePhoto.getId()).getInputStream();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return ResponseEntity.ok(new PhotoResource(photo));
    }

    @RequestMapping(value = "/disk/{key}/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<PhotoResource> readPhotoResourceFromDisk(@PathVariable String key) {
        File rootFolder = new File(BootRest.ROOT);
        File file = stream(rootFolder.listFiles()).peek(f->log.info("{}",f.getName())).filter(f -> key.equals(f.getName())).findFirst().get();
        log.info("file : {}",file);
        PhotoResource ps = new PhotoResource(() -> FileOperations.convertByteToInputStream(FileOperations.fileToBytes(file)));
        // Photo photo = () -> this.fs.getResource(dogePhoto.getId()).getInputStream();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return ResponseEntity.ok(ps);
    }

}
