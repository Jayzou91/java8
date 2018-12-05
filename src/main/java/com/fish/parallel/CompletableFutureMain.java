package com.fish.parallel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/7/22
 */
public class CompletableFutureMain {

    private Map<Integer, Product> cache = new HashMap<>();

    private Product getLocal(int id) {
        return cache.get(id);
    }

    private Product getRemote(int id) {
        try {
            Thread.sleep(100);
            if (id == 666) {
                throw new RuntimeException("Evil request.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Product(id, "default");
    }

    public CompletableFuture<Product> getProduct(int id) {
        try {
            Product product = getLocal(id);
            if (product != null) {
                return CompletableFuture.completedFuture(product);
            } else {
                CompletableFuture<Product> future = new CompletableFuture<>();
                Product p = getRemote(id);
                cache.put(id, p);
                future.complete(p);
                return future;
            }
        } catch (Exception e) {
            CompletableFuture<Product> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;
        }
    }


    public static void main(String[] args) throws Exception {
        CompletableFutureMain demo = new CompletableFutureMain();
        testException(demo);
    }

    private static void testException(CompletableFutureMain demo) throws ExecutionException, InterruptedException {
        demo.getProduct(666).get();
    }
}
