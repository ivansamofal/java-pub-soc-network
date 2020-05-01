package hello.services;

import hello.concurrency.*;
import hello.concurrency.atomic.AtomicIntegerExample;
import hello.concurrency.atomic.ReadWriteLockExample;
import hello.concurrency.atomic.ReentrantLockExample;
import hello.concurrency.atomic.ReentrantLockMethodsExample;
import hello.concurrency.executors.ExecutorsExample;
import hello.concurrency.executors.ScheduledExecutorsExample;
import hello.concurrency.executors.ScheduledExecutorsPeriodicExample;
import hello.concurrency.issues.*;
import hello.concurrency.runnable.*;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public void doSomething(String code) throws Exception {
        String[] args = new String[]{};
        switch (code) {
            case "FutureCancelExample":
                FutureCancelExample.main(args);
                break;
            case "FutureAndCallableExample":
                FutureAndCallableExample.main(args);
                break;
            case "FutureIsDoneExample":
                FutureIsDoneExample.main(args);
                break;
            case "InvokeAllExample":
                InvokeAllExample.main(args);
                break;
            case "InvokeAnyExample":
                InvokeAnyExample.main(args);
                break;

            case "RunnableExample":
                RunnableExample.main(args);
                break;
            case "RunnableExampleAnonymousClass":
                RunnableExampleAnonymousClass.main(args);
                break;
            case "RunnableExampleLambdaExpression":
                RunnableExampleLambdaExpression.main(args);
                break;
            case "ThreadExample":
                ThreadExample.main(args);
                break;
            case "ThreadJoinExample":
                ThreadJoinExample.main(args);
                break;
            case "ThreadSleepExample":
                ThreadSleepExample.main(args);
                break;

            case "MemoryConsistencyErrorExample":
                MemoryConsistencyErrorExample.main(args);
                break;
            case "RaceConditionExample":
                RaceConditionExample.main(args);
                break;
            case "SynchronizedBlockExample":
                SynchronizedBlockExample.main(args);
                break;
            case "SynchronizedMethodExample":
                SynchronizedMethodExample.main(args);
                break;
            case "VolatileKeywordExample":
                VolatileKeywordExample.main(args);
                break;

            case "ExecutorsExample":
                ExecutorsExample.main(args);
                break;
            case "ScheduledExecutorsExample":
                ScheduledExecutorsExample.main(args);
                break;
            case "ScheduledExecutorsPeriodicExample":
                ScheduledExecutorsPeriodicExample.main(args);
                break;

            case "AtomicIntegerExample":
                AtomicIntegerExample.main(args);
                break;
            case "ReadWriteLockExample":
                ReadWriteLockExample.main(args);
                break;
            case "ReentrantLockExample":
                ReentrantLockExample.main(args);
                break;
            case "ReentrantLockMethodsExample":
                ReentrantLockMethodsExample.main(args);
                break;
        }
    }
}
