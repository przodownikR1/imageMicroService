package pl.java.scalatech.service;

import static org.imgscalr.Scalr.OP_ANTIALIAS;

import java.awt.image.BufferedImage;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;

@FunctionalInterface
public interface ImageTransform {

    public  BufferedImage resize(BufferedImage img, int width, int height) ;

    public default BufferedImage resizeDefault(BufferedImage img, int width, int height) {
        BufferedImage thumbnail = Scalr.resize(img, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT, width, height, Scalr.OP_ANTIALIAS);
        return thumbnail;
    }

    public default BufferedImage resizeUltraQuality(BufferedImage image, int width, int height) {
        return Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, width, height, Scalr.OP_ANTIALIAS);
    }
  
    public static BufferedImage resizeQuality(BufferedImage img, int width, int height) {
        return Scalr.resize(img, Method.QUALITY, Mode.AUTOMATIC, width, height, Scalr.OP_ANTIALIAS);
    }

    public default BufferedImage rotate(BufferedImage src, String rotation) {
        return Scalr.rotate(src, Scalr.Rotation.valueOf(rotation));
    }

    public default BufferedImage getScaledImageWithImgscalr(BufferedImage src, int w, int h) {
        BufferedImage resizedImg;
        if (src.getWidth() > src.getHeight()) {
            resizedImg = Scalr.resize(src, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH, w, h, Scalr.OP_ANTIALIAS);
            if (resizedImg.getHeight() > h) {
                resizedImg = Scalr.resize(resizedImg, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_HEIGHT, w, h, OP_ANTIALIAS);
            }
        } else {
            resizedImg = Scalr.resize(src, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_HEIGHT, w, h, Scalr.OP_ANTIALIAS);
            if (resizedImg.getWidth() > w) {
                resizedImg = Scalr.resize(resizedImg, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH, w, h, Scalr.OP_ANTIALIAS);
            }
        }
        return resizedImg;
    }
}