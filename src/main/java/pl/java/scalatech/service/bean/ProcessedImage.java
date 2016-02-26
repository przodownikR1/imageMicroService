package pl.java.scalatech.service.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.java.scalatech.service.fixed.ImageFormat;
import pl.java.scalatech.service.fixed.ThumbnailType;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessedImage {
    private int width;
    private int heigth;
    private ImageFormat format;
    private ThumbnailType[] thumbnailType;
}