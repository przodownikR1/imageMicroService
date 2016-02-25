package pl.java.scalatech.service.bean;

import java.awt.image.BufferedImage;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ProcessedImage {
	private final String fileName;
	private final BufferedImage image;
}