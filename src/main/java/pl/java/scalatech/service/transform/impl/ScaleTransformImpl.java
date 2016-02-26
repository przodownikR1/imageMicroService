package pl.java.scalatech.service.transform.impl;

import java.awt.image.BufferedImage;
import java.io.File;

import org.springframework.stereotype.Component;

import pl.java.scalatech.service.transform.ScaleTransform;

@Component
public class ScaleTransformImpl implements ScaleTransform {

    @Override
    public BufferedImage scale(File f, int targetSize) {
        return scaleNormal(f, targetSize);
    }

}
