package pl.java.scalatech.web.images;

import static java.util.Arrays.stream;
import static java.util.Comparator.comparingLong;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static javax.imageio.ImageIO.write;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.Principal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.sun.jndi.toolkit.url.Uri;

import pl.java.scalatech.service.fixed.ThumbnailType;
import pl.java.scalatech.service.fixed.ImageFormat;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.BootRest;
import pl.java.scalatech.service.bean.ProcessedImage;
import pl.java.scalatech.service.transform.ImageTransform;
import pl.java.scalatech.web.images.dto.ImageSubmission;
import pl.java.scalatech.web.images.dto.Photo;

@Controller
@RequestMapping("/upload")
@Slf4j
public class ImageUploadController {
    private final static String ANONYMOUS = "anonymous";

    @RequestMapping(value = "/")
    public String fileUpload(Model model) {
        reloadFileToModel(model);
        return "upload";
        
    }
    @SneakyThrows
    private void reloadFileToModel(Model model) {
        
        File rootFolder = new File(BootRest.ROOT);
        List<String> fileNames = stream(rootFolder.listFiles()).map(f -> f.getName()).collect(toList());
        model.addAttribute("files", stream(rootFolder.listFiles()).sorted(comparingLong(f -> -1 * f.lastModified())).map(f ->
        ImageSubmission.builder().fileName(f.getName()).size(f.length()).url(null).build()).collect(toList()));
    }
    
    @Autowired
    private ImageTransform imageTransform;

    ThumbnailType[] tab = { ThumbnailType.EXTRA, ThumbnailType.SMALL,ThumbnailType.LARGE,ThumbnailType.EXTRA_LARGE };

    @RequestMapping(value = "/", method = { RequestMethod.POST, RequestMethod.PUT }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "upload single file", httpMethod = "POST", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    //@ResponseBody
    // @formatter:off
    /*ResponseEntity<?>*/
    String handleFormUpload(Principal principal, @RequestParam("name") String name, @RequestParam("file") MultipartFile file, UriComponentsBuilder uriBuilder) throws IOException {     
        log.info("name : {}  file:  {} : size : {}", name, file.getOriginalFilename(), file.getSize());
        String userId = ofNullable(principal).map(s -> s.getName()).orElseGet(() -> ANONYMOUS);
        BufferedImage bi = imageTransform.convertInputStreamImage(file.getInputStream());
        ProcessedImage image = ProcessedImage.builder().format(ImageFormat.JPG).thumbnailType(tab).width(bi.getWidth()).heigth(bi.getHeight()).build();
        try (InputStream inputStream = file.getInputStream()) {
            for (ThumbnailType type : tab) {
                createThumbnail(file, bi, image, type);
            }
        }
        // TODO
        // create link to photo
        URI uri = uriBuilder.path("/photo").buildAndExpand(userId).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        //return new ResponseEntity<>(headers, HttpStatus.CREATED);
        return "redirect:/upload/";
    }
    //  @formatter:on
    
    
   

    private void createThumbnail(MultipartFile file, BufferedImage bi, ProcessedImage image, ThumbnailType type) throws IOException {
        BufferedImage tempImg = imageTransform.resize(bi, type.getWidth(), type.getHeigth());
        String fileName = file.getOriginalFilename().replace('.', '_') + "_" + type.name().toLowerCase() + "_" + image.getFormat().name().toLowerCase();
        log.info("++++createThumbnail  : {}",fileName);
//        HashCode hashCode = Hashing.md5().hashBytes(fileName.getBytes());
        write(tempImg, image.getFormat().name(), new File(BootRest.ROOT + "/"+fileName));
    }

    
    
    
    @RequestMapping(method = RequestMethod.POST, value = "{userId}/doge")
    public ResponseEntity<?> postDogePhoto(@PathVariable String userId, @RequestParam MultipartFile file, UriComponentsBuilder uriBuilder) throws IOException {
        Photo photo = file::getInputStream;
        URI uri = uriBuilder.path("/poll/{userId}").buildAndExpand(userId, userId).toUri();
        return ResponseEntity.created(uri).build();
    }

    private File severFileSave(String name, byte[] bytes) throws IOException {
        String rootPath = System.getProperty("user.home");
        File dir = new File(rootPath + File.separator + "tmpFiles");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
            stream.write(bytes);
        }
        return serverFile;
    }
}
