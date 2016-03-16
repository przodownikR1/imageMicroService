package pl.java.scalatech.web.images.dto;

import java.net.URL;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class ImageSubmission {
	private final long id;
	private final String fileName;
	private final long size;
	private final URL url;
}
