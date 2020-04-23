package hello;

import java.util.concurrent.*;

public class NumberPrint implements Runnable {

    public void run() {

//        for (int i = 1; i <= 5; i++) {
//
//            System.out.println(i);
//
//            try {
//
//                Thread.sleep(2000);
//
//            } catch (InterruptedException ex) {
//                System.out.println("I'm interrupted");
//            }
//        }
        for (int i = 1; i <= 10; i++) {
            System.out.println("This is message #" + i);

            try {
                Thread.sleep(2000);
                continue;
            } catch (InterruptedException ex) {
                System.out.println("I'm resumed");
            }
        }
    }

    public void execute() {
//        Runnable task = new NumberPrint();
//        Thread thread = new Thread(task);
//        thread.start();

        Thread t1 = new Thread(new NumberPrint());
        t1.start();

        try {
            Thread.sleep(5000);
            t1.interrupt();

        } catch (InterruptedException ex) {
            // do nothing
            System.out.println("interrupt");
        }
    }
}