package pl.java.scalatech.image;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.SneakyThrows;
import pl.java.scalatech.config.PhotoServerConfig;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PhotoServerConfig.class)
@ActiveProfiles("test")
public class ImageTest {

    @Autowired
    private Resource picture;
    
    @Test
    @SneakyThrows
    public void test(){
        BufferedImage image = ImageIO.read(picture.getInputStream());

        image = Scalr.crop(image,150,150,500,150,Scalr.OP_ANTIALIAS);

        BufferedImage resizeImage =
                Scalr.resize(image, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT,
                        350, 350, Scalr.OP_ANTIALIAS);

        ImageIO.write(resizeImage, "PNG", new FileOutputStream("resImg.png"));
        ImageIO.write(resizeImage, "JPG", new FileOutputStream("resImg.jpg"));
    }
}
