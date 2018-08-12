package com.funny.java.stream;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/7/22
 */
public class ForkJoinPoolMain {
    public static void main(String[] args) {
        System.out.println("Available processors count: " + Runtime.getRuntime().availableProcessors());
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        long total = LongStream.rangeClosed(1, 3_000_000).parallel().sum();
        int poolSize = ForkJoinPool.commonPool().getPoolSize();
        System.out.println("Pool size: " + poolSize);

        customizeForkJoinPool();
    }

    private static void customizeForkJoinPool() {
        ForkJoinPool pool = new ForkJoinPool(15);
        ForkJoinTask<Long> task = pool.submit(() -> LongStream.rangeClosed(1, 3_000_000).parallel().sum());
        try{
            long total = task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
        int poolSize = pool.getPoolSize();
        System.out.println("Pool size: " + poolSize);
    }

}
