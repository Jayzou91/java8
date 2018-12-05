package com.fish.multithread;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/7/22
 */
public class AccumulatorMain {
    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        AtomicLong counter = new AtomicLong(0);
        doAccumulator(counter);
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        System.out.println("AtomicLong------The result is " + counter.get() + ", it consumes " + timeElapsed + " ms.");

        start = Instant.now();
        LongAdder longAdder = new LongAdder();
        doAccumulator(longAdder);
        end = Instant.now();
        timeElapsed = Duration.between(start, end).toMillis();
        System.out.println("LongAdder------The result is " + longAdder.longValue() + ", it consumes " + timeElapsed + " ms.");
    }

    private static void doAccumulator(final AtomicLong counter) throws InterruptedException {
        int threadCount = 1000;
        ExecutorService exec = Executors.newFixedThreadPool(threadCount);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            exec.submit(new AccumulatorByAtomicLong(counter, countDownLatch));
        }
        countDownLatch.await();
        exec.shutdown();
    }

    private static void doAccumulator(final LongAdder counter) throws InterruptedException {
        int threadCount = 1000;
        ExecutorService exec = Executors.newFixedThreadPool(threadCount);
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            exec.submit(new AccumulatorByLongAdder(counter, countDownLatch));
        }
        countDownLatch.await();
        exec.shutdown();
    }
}
