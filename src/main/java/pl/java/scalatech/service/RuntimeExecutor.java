package pl.java.scalatech.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;

import pl.java.scalatech.exception.RuntimeExecutorTimeoutException;

public class RuntimeExecutor {

    private RuntimeExecutor() {
    }

    public static void execute(String command, long timeout) throws RuntimeExecutorTimeoutException, IOException {
        // log.debug("About to execute cmd: " + command + " with timeout " + timeout + "s.");
        Timer timer = null;
        Process process = null;
        InputStream in = null;
        OutputStream out = null;
        InputStream err = null;
        try {
            process = Runtime.getRuntime().exec(command);
            in = process.getInputStream();
            out = process.getOutputStream();
            err = process.getErrorStream();
            timer = new Timer();
            timer.schedule(new TimeoutTimer(Thread.currentThread()), TimeUnit.SECONDS.toMillis(timeout));

            int result = process.waitFor(); // wait while process finishes
            if (result != 0) {
                String error = "";
                if (err != null) {
                    byte[] errOut = IOUtils.toByteArray(err);
                    if (null != errOut) {
                        error = new String(errOut);
                    }
                }
                // log.error("Failed execute command: '" + command + "'. Error message: " + error);
            } else {
                if (in != null) {
                    String msg = new String(IOUtils.toByteArray(in));
                    // log.debug("Command " + command + " executed with message: " + msg);
                }
            }

        } catch (InterruptedException e) {
            try {
                process.destroy();
            } catch (Exception e2) {
                // ignore
            }
            throw new RuntimeExecutorTimeoutException("Failed to execute command " + command + " because timeout limit " + timeout + "s exceeded", null);
        } finally {
            if (timer != null) {
                try {
                    timer.cancel();
                } catch (Exception e) {
                    // ignore
                }
            }
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(err);
        }
    }

    private static class TimeoutTimer extends TimerTask {
        private Thread target = null;

        public TimeoutTimer(Thread target) {
            this.target = target;
        }

        @Override
        public void run() {
            target.interrupt();
        }

    }

}