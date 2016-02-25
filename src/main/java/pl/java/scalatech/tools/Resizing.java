package pl.java.scalatech.tools;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Resizing {
    private BufferedImage img;
    private int width;
    private int height;
        
    public static Image getImageFromResourceAsStream(String fileName, Class<?> classForPath) throws IOException {
        Image result = ImageIO.read(classForPath.getResourceAsStream(fileName));
        return result;
    }

    public static Image getImage(Path fileName) throws IOException {
        Image result = ImageIO.read(fileName.toFile());
        return result;
    }
             
    public static void saveBufferedImage(BufferedImage image, File file) throws IOException {
        ImageIO.write(image, "jpg", file);
    }
}