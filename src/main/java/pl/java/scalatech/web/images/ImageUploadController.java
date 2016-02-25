package pl.java.scalatech.web.images;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.Principal;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.io.Files;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.tools.FileOperations;
import pl.java.scalatech.web.images.dto.Photo;

@Controller
@RequestMapping("/upload")
@Slf4j
public class ImageUploadController {

    @RequestMapping(value = "/")
    public String fileUpload() {
        return "upload";
    }
    
    @RequestMapping(value = "/sample")
    public String sample() {
        return "sample";
    }
    
    @RequestMapping(value = "/", method = { RequestMethod.POST, RequestMethod.PUT }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "upload single file", httpMethod = "POST", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    ResponseEntity<?> handleFormUpload(Principal principal, @RequestParam("name") String name, @RequestParam("file") MultipartFile file, UriComponentsBuilder uriBuilder) throws IOException {
        log.info("name : {}  file:  {} : size : {}",name,file.getOriginalFilename(),file.getSize());
        String userId = principal.getName();
        String fileName = file.getName();
        try (InputStream inputStream = file.getInputStream()) {
            Files.write(FileOperations.convertInputStreamToByte(inputStream), new File(fileName));
        }
        //TODO
        //create link to photo 
        URI uri = uriBuilder.path("/photo").buildAndExpand(userId).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "{userId}/doge")
    public ResponseEntity<?> postDogePhoto(@PathVariable String userId,
            @RequestParam MultipartFile file, UriComponentsBuilder uriBuilder)
            throws IOException {
        Photo photo = file::getInputStream;

        URI uri = uriBuilder.path("/poll/{userId}").buildAndExpand(userId, userId).toUri();
        
        return ResponseEntity.created(uri).build();
    }


    private File severFileSave(String name, byte[] bytes) throws FileNotFoundException, IOException {
        String rootPath = System.getProperty("user.home");
        File dir = new File(rootPath + File.separator + "tmpFiles");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // Create the file on server
        File serverFile = new File(dir.getAbsolutePath()
                + File.separator + name);
        BufferedOutputStream stream = new BufferedOutputStream( new FileOutputStream(serverFile));
        stream.write(bytes);
        stream.close();
        return serverFile;
    }
    

    
    
    @RequestMapping(value = "/upload2", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, method = RequestMethod.POST)
    public ResponseEntity<Void> uploadFile(@RequestPart String description, @RequestPart MultipartFile file) {
     
        return ResponseEntity.ok(null);
    }
}
