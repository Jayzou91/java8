package com.fish.parallel;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/7/22
 */
public class FutureMain {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<String> future = exec.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(100);
                return "Hello, World!";
            }
        });
        System.out.println("Processing");

        future = exec.submit(() -> {
            try {
                Thread.sleep(10);
                return "Hello, World!";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        });
        System.out.println("More processing");

        while (!future.isDone()) {
            System.out.println("Waiting");
        }

        getIfNotCancelled(future);
    }

    private static void getIfNotCancelled(Future<String> future) {
        try {
            if (!future.isCancelled()) {
                System.out.println(future.get());
            } else {
                System.out.println("Cancelled");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
