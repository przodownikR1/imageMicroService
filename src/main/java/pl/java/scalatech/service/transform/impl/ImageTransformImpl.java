package pl.java.scalatech.service.transform.impl;

import java.awt.image.BufferedImage;

import org.springframework.stereotype.Component;

import pl.java.scalatech.service.transform.ImageTransform;
@Component
public class ImageTransformImpl  implements ImageTransform {

    @Override
    public BufferedImage resize(BufferedImage img, int width, int height) {
        return resizeDefault(img, width, height);
    }
    
}
