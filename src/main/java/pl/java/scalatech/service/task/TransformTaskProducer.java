package pl.java.scalatech.service.task;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.service.ImageTransformTask;
import pl.java.scalatech.service.ImgTransformService;
@Slf4j
public class TransformTaskProducer implements Runnable {
        @Autowired
        private ImgTransformService imgTransformService;
        
        private ImageTransformTask task;

        public TransformTaskProducer(ImageTransformTask task) {
            this.task = task;
        }

        @Override
        public void run() {
            try {
                imgTransformService.getTransformTasks().put(task);
                log.debug("Transform task submitted: " + task);
            } catch (InterruptedException e) {
                log.error("TransformTaskProducer interrupted.", e);
            }
        }
    }
