package hello.controllers;

import hello.entities.Row;
import hello.services.KafkaService;
import hello.services.RowService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
class HelloController {
    final private RowService rowService;
    final private KafkaService kafkaService;

    public HelloController(RowService rowService, KafkaService kafkaService) {
        this.rowService = rowService;
        this.kafkaService = kafkaService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) throws Exception {
//        System.out.println("some");
//        Thread someThread = new Thread(new Test());
//        Runnable task = () -> {
//            String threadName = Thread.currentThread().getName();
//            System.out.println("Hello " + threadName);
//        };
//
//        task.run();
//
//        Thread thread = new Thread(task);
//        thread.start();
//
//        System.out.println("Done!");
//
//        someThread.start();

//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.submit(() -> {
//            String threadName = Thread.currentThread().getName();
//            System.out.println("Hello " + threadName);
//        });
//
//        try {
//            System.out.println("attempt to shutdown executor");
//            executor.shutdown();
//            executor.awaitTermination(5, TimeUnit.SECONDS);
//        }
//        catch (InterruptedException e) {
//            System.err.println("tasks interrupted");
//        }
//        finally {
//            if (!executor.isTerminated()) {
//                System.err.println("cancel non-finished tasks");
//            }
//            executor.shutdownNow();
//            System.out.println("shutdown finished");
//        }


//        Callable task = () -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//                return 123;
//            }
//            catch (InterruptedException e) {
//                throw new IllegalStateException("task interrupted", e);
//            }
//        };
//
//        ExecutorService executor = Executors.newFixedThreadPool(1);
//        Future<Integer> future = executor.submit(task);
//
//        System.out.println("future done? " + future.isDone());
//
//        Integer result = future.get();
//
//        System.out.println("future done? " + future.isDone());
//        System.out.print("result: " + result);

//        InvokeAllExecutor invokeAllExecutor = new InvokeAllExecutor();
//        invokeAllExecutor.execute();
//        InvokeAnyExecutor invokeAnyExecutor = new InvokeAnyExecutor();
//        invokeAnyExecutor.execute();

//        ScheduledExecutor scheduledExecutor = new ScheduledExecutor();
//        scheduledExecutor.execute();
//        scheduledExecutor.fixedExecute();

//        SomeChild some = new SomeChild();
//        some.some();

//        LockExample lockExample = new LockExample();
////        lockExample.execute();
//        lockExample.executeReadWriteLock();
//
//        SemaphoneExample semaphoneExample = new SemaphoneExample();
//        semaphoneExample.execute();


//        MultiTestt _test = new MultiTestt();
//        _test.execute();

//          CsvParser.parseProductCsv("/tmp/test.csv");
        rowService.execute();

        kafkaService.testProducer();

//        FileReaderExample _example = new FileReaderExample();
//        _example.execute();

//        FileStreamExample fileStreamExample = new FileStreamExample();
//        fileStreamExample.execute();

//        NumberPrint _print = new NumberPrint();
//        _print.execute();

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username = authentication.getName();
        System.out.println("CURRENT USERNAME IS: " + username);
        Object principal = authentication.getPrincipal();
        System.out.println(principal);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(_i -> {
            System.out.println(_i);
        });

        return "Hi223 " + name + " !";
    }

    @GetMapping("/consumer")
    public String consumer() {
        kafkaService.testConsumer();

        return "test";
    }

    @GetMapping("/stream")
    public String stream() {
        kafkaService.testStream();

        return "stream";
    }

    @GetMapping("/rows")
    public List<Row> all() {
        return rowService.findAll();
    }
}