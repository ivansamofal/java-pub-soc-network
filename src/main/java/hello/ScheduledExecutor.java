package hello;

import java.util.concurrent.*;
import java.lang.IllegalStateException;
import java.util.Arrays;
import java.util.List;

class ScheduledExecutor {
    public void execute() throws Exception {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling2: " + System.nanoTime());
        ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

        TimeUnit.MILLISECONDS.sleep(1337);

        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay: %sms", remainingDelay);
    }

    public void fixedExecute() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling3: " + System.nanoTime());
            }
            catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
    }
}