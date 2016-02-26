package pl.java.scalatech.service.bean;

import java.io.File;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
	private File destSrc;
	private ProcessedImage image;	
	private String folderId;	
	
		
}