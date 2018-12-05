package com.fish.multithread.deadlock;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/12/5
 */
public class DeadLockDiningPhilosophers {
    public static void main(String[] args) throws InterruptedException, IOException {
        int poder = 5;
        if (args.length > 0) {
            poder = Integer.parseInt(args[0]);
        }
        int size = 5;
        if (args.length > 1) {
            size = Integer.parseInt(args[1]);
        }

        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] sticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size], i, poder));
            if (args.length == 3 && args[2].equals("timeout")) {
                TimeUnit.SECONDS.sleep(5);
            } else {
                System.out.println("Press 'Enter' to quit");
                System.in.read();
            }
        }
    }
}

class Chopstick {
    private boolean taken = false;

    public synchronized void take() throws InterruptedException {
        while (taken) {
            wait();
        }
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}

class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random();

    public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }

        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " thinking...");
                pause();
                System.out.println(this + " grabbing left chopstick...");
                left.take();
                System.out.println(this + " grabbing right chopstick...");
                right.take();
                System.out.println(this + " eating...");
                pause();
                left.drop();
                right.drop();
            }
        } catch (InterruptedException e) {
            System.out.println(this + " exiting via interrupt...");
        }
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }
}
