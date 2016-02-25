package pl.java.scalatech.service.task;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.service.ImageTransformTask;
import pl.java.scalatech.service.ImgTransformService;

@Slf4j
public  class TransformTaskConsumer implements Runnable {
     @Autowired
     private ImgTransformService imgTransformService; 
    
        @Override
        public void run() {
            try {
                while (true) {
                    ImageTransformTask task = imgTransformService.getTransformTasks().take();
                    executeTask(task);
                }
            } catch (InterruptedException e) {
                log.error("TransformTaskConsumer interrupted.", e);
            }
        }

        private void executeTask(ImageTransformTask task) {
            // TODO Auto-generated method stub
            
        }
    }