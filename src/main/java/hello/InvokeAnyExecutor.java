package hello;

import java.util.concurrent.*;
import java.lang.IllegalStateException;
import java.util.Arrays;
import java.util.List;

class InvokeAnyExecutor
{
    private Callable callable(String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }

    public void execute() throws Exception {
//        Callable callable = (String result, long sleepSeconds) -> {
//            return () -> {
//                TimeUnit.SECONDS.sleep(sleepSeconds);
//                return result;
//            };
//        };



        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 2),
                callable("task2", 5),
                callable("task3", 3));

        String result = executor.invokeAny(callables);
        System.out.println(result);
    }
}