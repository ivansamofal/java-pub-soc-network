package hello;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.lang.IllegalStateException;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

class AbstractLock {
    protected static void stop(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("termination interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("killing non-finished tasks");
            }
            executor.shutdownNow();
        }
    }

    protected static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}