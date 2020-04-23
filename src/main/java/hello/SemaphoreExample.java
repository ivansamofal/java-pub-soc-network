package hello;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.lang.IllegalStateException;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class SemaphoneExample extends AbstractLock {
    public void execute() {
        ExecutorService executor = Executors.newFixedThreadPool(15);

        Semaphore semaphore = new Semaphore(7);

        Runnable longRunningTask = () -> {
            boolean permit = false;
            try {
                permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                if (permit) {
                    System.out.println("Semaphore acquired");
                    super.sleep(5);
                } else {
                    System.out.println("Could not acquire semaphore");
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            } finally {
                if (permit) {
                    semaphore.release();
                }
            }
        };

        IntStream.range(0, 100)
                .forEach(i -> executor.submit(longRunningTask));

        super.stop(executor);
    }
}
