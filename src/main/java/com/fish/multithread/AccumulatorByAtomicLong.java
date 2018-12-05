package com.fish.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/7/22
 */
public class AccumulatorByAtomicLong implements Runnable {

    private AtomicLong counter;
    private CountDownLatch countDownLatch;

    public AccumulatorByAtomicLong(AtomicLong counter, CountDownLatch countDownLatch) {
        this.counter = counter;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            counter.incrementAndGet();
        }
        countDownLatch.countDown();
    }
}
