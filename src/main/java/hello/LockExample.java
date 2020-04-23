package hello;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.lang.IllegalStateException;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

class LockExample extends AbstractLock
{
    public void execute() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ReentrantLock lock = new ReentrantLock();

        executor.submit(() -> {
            lock.lock();
            try {
                sleep(1);
            } finally {
                lock.unlock();
            }
        });

        executor.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });

        stop(executor);
    }

    public void executeReadWriteLock() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        Runnable readTask = () -> {
            lock.readLock().lock();
            try {
                System.out.println(map.get("foo"));
                sleep(10);
            } finally {
                lock.readLock().unlock();
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);

        stop(executor);
    }
}
