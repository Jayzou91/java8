package com.fish.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/7/19
 */
public class AddThread implements Callable<Integer> {
    private int num;
    private int thresholdPerThread;
    private CountDownLatch countDownLatch;

    public AddThread(int thresholdPerThread, CountDownLatch countDownLatch) {
        this.num = 0;
        this.thresholdPerThread = thresholdPerThread;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        while (true) {
            if (count >= thresholdPerThread) {
                countDownLatch.countDown();
                break;
            } else {
                num++;
                count++;
            }
        }
        return num;
    }
}
