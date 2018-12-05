package com.fish.multithread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/12/5
 */
public class PipedIO {

    public static void main(String[] args) throws IOException, InterruptedException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit(sender);
        exec.submit(receiver);
        TimeUnit.MILLISECONDS.sleep(5);
        exec.shutdown();
    }
}

class Sender implements Runnable {
    private Random rand = new Random();
    private PipedWriter out = new PipedWriter();

    public PipedWriter getPipedWriter() {
        return out;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (char c = 'A'; c <= 'z'; c++) {
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e + " Sender sleep exception.");
        } catch (IOException e) {
            System.out.println(e + " Sender write exception.");
        }
    }
}

class Receiver implements Runnable {

    private PipedReader in;

    public Receiver(Sender sender) throws IOException {
        this.in = new PipedReader(sender.getPipedWriter());
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Receive " + (char) in.read());
            }
        } catch (IOException e) {
            System.out.println(e + " Receiver read exception.");
        }
    }
}
