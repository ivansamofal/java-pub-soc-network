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

class MultiTest {
    public void execute() {
        long start = System.nanoTime();

        List _list = new ArrayList<Integer>();
        for (int i = 0; i <= 1000; i++) {
            if (_list.size() < 1000) {
                _list.add(i);
            }
//            System.out.println("Work: " + i);
        }
        System.out.println(_list.size());


        System.out.println(System.nanoTime() - start);


        long start2 = System.nanoTime();
//        List _list2 = new ArrayList<>();
        List _list2 = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Runnable longRunningTask = () -> {
            try {
                if (_list2.size() < 1000) {
                    _list2.add(10);
                }
//                System.out.println("Work parallel: " + Thread.currentThread().getName());
//                executor.shutdown();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        IntStream.range(0, 1000)
                .forEach(i -> {
                    executor.execute(longRunningTask);
                });

        System.out.println(_list2.size());
        System.out.println(Arrays.deepToString(_list2.toArray()));
        System.out.println(System.nanoTime() - start2);
    }
}
