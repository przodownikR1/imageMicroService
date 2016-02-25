package pl.java.scalatech.service;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.service.task.TransformTaskConsumer;
import pl.java.scalatech.service.task.TransformTaskProducer;
import pl.java.scalatech.service.tools.GalleryUtils;

@Data
@Slf4j
@Component
public class ImgTransformService {
    private BlockingQueue<ImageTransformTask> transformTasks;

    private ExecutorService consumerExecutor;
    private ExecutorService transformExecutor;
    private ExecutorService producerExecutor;

    public ImgTransformService() {

        this.transformTasks = new LinkedBlockingQueue<>(10);

        consumerExecutor = Executors.newFixedThreadPool(10);
        transformExecutor = Executors.newFixedThreadPool(10);
        producerExecutor = Executors.newFixedThreadPool(1);
        consumerExecutor.submit(new TransformTaskConsumer());
    }

    public void addToResize(File sourceJpeg, String folderId, boolean thumb, boolean view) {

        if (!thumb && !view) { return; }

        String sourceFileName = sourceJpeg.getName();
        String pathToJpeg = sourceJpeg.getPath();

        // create thumb
        if (thumb) {
            String thumbFilePath = GalleryUtils.getThumbPath(sourceFileName, folderId, "storePath");
            ImageTransformTask thumbTask = new ImageTransformTask(pathToJpeg, thumbFilePath, 34, folderId, sourceFileName);
            producerExecutor.submit(new TransformTaskProducer(thumbTask));
        }

    }

}