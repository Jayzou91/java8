package com.fish.parallel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/12/4
 */
public class ThreadTest {

    public static void main(String[] args) {
        while (true) {
            ExecutorService service = Executors.newFixedThreadPool(1);
            try {
                service.submit(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                    }
                });
            } catch (Exception e) {
            } finally {
                service.shutdown();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        }
    }

}
