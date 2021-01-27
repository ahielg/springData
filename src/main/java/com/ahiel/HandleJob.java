package com.ahiel;

import java.util.List;
import java.util.concurrent.*;

public class HandleJob {
    private static final int THREAD_NUMBER = 5;
    ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_NUMBER);


    public CountDownLatch doJob(List<Callable> jobs) {
        CountDownLatch doneSignal = new CountDownLatch(jobs.size());

        for (Callable job : jobs) {
            CompletableFuture<Void> cf = CompletableFuture.allOf();

            threadPool.submit(() -> {
                try {
                    Object call = job.call();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    doneSignal.countDown();
                }
            });
        }
        return doneSignal;
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("a");
        StringBuffer sf = new StringBuffer("b");
        sb.append(sf);
        System.out.println(sb);
    }
}
