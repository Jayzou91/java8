package com.fish.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/7/22
 */
public class AccumulatorByLongAdder implements Runnable {

    private LongAdder counter;
    private CountDownLatch countDownLatch;

    public AccumulatorByLongAdder(LongAdder counter, CountDownLatch countDownLatch) {
        this.counter = counter;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            counter.increment();
        }
        countDownLatch.countDown();
    }
}
