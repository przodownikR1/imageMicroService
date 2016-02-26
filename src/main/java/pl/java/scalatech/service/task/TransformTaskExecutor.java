package pl.java.scalatech.service.task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.service.ImageTransformTask;

@Slf4j
@RequiredArgsConstructor
public class TransformTaskExecutor implements Runnable {

    private final @NonNull ImageTransformTask task;

    @Override
    public void run() {
        Stopwatch sw = Stopwatch.createStarted();
        try (InputStream fis = new FileInputStream(task.getSource()); OutputStream fos = new FileOutputStream(task.getDestSrc())) {
            //TODO
            sw.stop();
            log.debug("Finished {} to resize : time :{}", task.getSourceFileName(), sw.elapsed(TimeUnit.MILLISECONDS));

        } catch (FileNotFoundException e) {
            log.error("{}", e);
        } catch (IOException e) {
            log.error("{}", e);
        }
    }

}