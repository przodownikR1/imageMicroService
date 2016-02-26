package pl.java.scalatech.service;

import java.io.File;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.service.fixed.ImageFormat;
import pl.java.scalatech.service.fixed.ThumbnailType;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageTransformTask {

	
    public String getSourceFileName() {
        return source.getName();
    }

    private File source;
	private ImageFormat format;
	private ThumbnailType[] thumbnailType;
	private File destSrc;
	private int width;
	private int heigth;
	private String folderId;	
	
		
}