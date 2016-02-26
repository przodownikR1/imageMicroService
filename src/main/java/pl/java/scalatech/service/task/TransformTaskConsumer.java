package pl.java.scalatech.service.task;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Stopwatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.service.ImgTransformService;
import pl.java.scalatech.service.bean.ImageTransformTask;
import pl.java.scalatech.service.transform.ImageTransform;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransformTaskConsumer implements Runnable {
    
    private final  ImgTransformService imgTransformService;    
    private final ImageTransform transform;

    private  void executeTask(ImageTransformTask task) {
        Stopwatch sw = Stopwatch.createStarted();
           Arrays.stream(task.getImage().getThumbnailType()).parallel().forEach(t -> {
            BufferedImage image = transform.resize(transform.convertFile2Image(task.getSource()), t.getWidth(), t.getHeigth());
            transform.saveBufferedImageToFile(image, task.getImage().getFormat(), task.getDestSrc());
        });
         
        sw.stop();
        log.info("Finished {} to resize : time :{}", task.getSourceFileName(), sw.elapsed(TimeUnit.MILLISECONDS));
    }

    @Override
    public void run() {
        while (true) {
            try {
                executeTask(imgTransformService.getTransformTasks().take());
            } catch (InterruptedException e) {
            }
        }
    }
}