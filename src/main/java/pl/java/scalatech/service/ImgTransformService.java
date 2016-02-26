package pl.java.scalatech.service;

import static java.util.concurrent.Executors.newFixedThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.service.task.TransformTaskConsumer;
import pl.java.scalatech.service.transform.ImageTransform;

@Data
@Slf4j
@Component
public class ImgTransformService {
    private BlockingQueue<ImageTransformTask> transformTasks;
    private ExecutorService consumerExecutor;
    private ExecutorService producerExecutor;
    private final  ImageTransform transform = null;
    
    @Autowired
    public ImgTransformService(ImageTransform imageTransform) {
        this.transformTasks = new LinkedBlockingQueue<>(10);
        consumerExecutor = newFixedThreadPool(10);        
        producerExecutor = newFixedThreadPool(1);     
        consumerExecutor.submit(new TransformTaskConsumer(this,imageTransform));
    }

    public void addToResize(ImageTransformTask task) {
            producerExecutor.submit(()->task);
    }
   
}