package hello;

import java.util.concurrent.*;
import java.lang.IllegalStateException;
import java.util.Arrays;
import java.util.List;

class InvokeAllExecutor
{
    public void execute() throws Exception {
        Callable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return "123";
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        Callable task2 = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return "456";
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        Callable task3 = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return "789";
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        List<Callable<String>> callables = Arrays.asList(
                task,
                task2,
                task3
        );

        ExecutorService executor = Executors.newWorkStealingPool(1);

        executor.invokeAll(callables).stream().map(future -> {
            try {
                return future.get();
            }
            catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }).forEach(System.out::println);
    }
}