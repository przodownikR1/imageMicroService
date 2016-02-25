package pl.java.scalatech.service.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.service.ImageTransform;
import pl.java.scalatech.service.ImageTransformTask;
import pl.java.scalatech.service.tools.GalleryUtils;

@Slf4j
public class TransformTaskExecutor implements Runnable {

        final ImageTransformTask task;
        ImageTransform imageTransform;
     

        public TransformTaskExecutor(ImageTransformTask task) {
            this.task = task;            
        }

        @Override
        public void run() {

            long startTime = System.currentTimeMillis();

            InputStream is = null;
            OutputStream os = null;
            File tmpCopy = null;
            try {
                File originalJpeg = new File(task.getSourceSrc());
                String tmpFilePath = FilenameUtils.concat("getTmpPath()", GalleryUtils.getTmpFileNameBase() + ".jpg");
                tmpCopy = new File(tmpFilePath);
                is = new FileInputStream(originalJpeg);
                os = new FileOutputStream(tmpCopy);
                IOUtils.copy(is, os);

                // imageTransform.resize(tmpCopy, task.getDestSrc(), task.getWidth());

                long executionTime = System.currentTimeMillis() - startTime;
                log.debug("Finished to resize (" + executionTime + "ms): " + task);


            } catch (FileNotFoundException e) {
                log.error("Failed to resize image: " + task, e);
            } catch (IOException e) {
                log.error("Failed to resize image: " + task, e);
            } finally {
                IOUtils.closeQuietly(is);
                IOUtils.closeQuietly(os);
                if (tmpCopy != null) {
                    String tmpPath = tmpCopy.getPath();
                    try {
                        tmpCopy.delete();
                    } catch (Exception e) {
                        log.debug("Failed to delete tmp file " + tmpPath);
                    }
                }
            }
        }

    }