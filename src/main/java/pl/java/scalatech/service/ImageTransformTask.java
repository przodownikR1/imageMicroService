package pl.java.scalatech.service;

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

	private String sourceSrc;
	private String destSrc;
	private int width;
	private String folderId;
	private String sourceFileName;
}