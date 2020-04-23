package hello;

import java.lang.System;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.lang.IllegalStateException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.stream.IntStream;

class MultiTestt {
    public void execute() {
        long start = System.nanoTime();

//        List _list = new ArrayList<Integer>();
        try {
            for (int i = 0; i <= 10; i++) {
                Thread.sleep(1000);
                System.out.println("Work: " + i);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
//        System.out.println(_list.size());


        System.out.println(System.nanoTime() - start);


//        long start2 = System.nanoTime();
//        List _list2 = new ArrayList<>();
//        List _list2 = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Runnable longRunningTask = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Work one: " + Thread.currentThread().getName());
//                System.out.println("Work parallel: " + Thread.currentThread().getName());
//                executor.shutdown();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        long start3 = System.nanoTime();
        IntStream.range(0, 10)
                .forEach(i -> {
                    System.out.println("gonna to execute");
                    executor.execute(longRunningTask);
                });

//        System.out.println(_list2.size());
//        System.out.println(Arrays.deepToString(_list2.toArray()));
        System.out.println(System.nanoTime() - start3);
    }
}
