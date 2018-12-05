package com.fish.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/7/19
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        for (int k = 0; k < 100; k++) {
            int threshold = 4;
            int threadCount = 3;
            int quotient = threshold / threadCount;
            int remainder = threshold % threadCount;
            ExecutorService exec = Executors.newFixedThreadPool(threadCount);
            final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
            List<Future<Integer>> result = new ArrayList<Future<Integer>>();
            for (int i = 1; i <= threadCount; i++) {
                int thresholdPerThread = (remainder-- > 0) ? quotient + 1 : quotient;
                result.add(exec.submit(new AddThread(thresholdPerThread, countDownLatch)));
            }
            countDownLatch.await();
            exec.shutdown();

            int sum = 0;
            for (Future<Integer> f : result) {
                try {
                    sum += f.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(k + " time, sum=" + sum + ".");
        }
    }
}
