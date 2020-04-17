package net.codingme.concurrent.chapter6;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * <p>
 *
 * @author niujinpeng
 * @Date 2020/4/8 22:15
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            System.out.println("start thread....");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end thread....");
            countDownLatch.countDown();
        });
        thread.start();
        Thread.sleep(1000);
        System.out.println(thread.isAlive());
        while (countDownLatch.getCount() != 0) {
        }
        System.out.println("end");
        System.out.println(thread.isAlive());
        System.out.println("----------------------");

        FutureTask<String> futureTask = new FutureTask<>(new ThreadTest());
        Thread thread1 = new Thread(futureTask);
        thread1.start();
        Thread.sleep(1000);
        System.out.println(futureTask.isDone());
        while (!futureTask.isDone()) {
        }
        System.out.println("end2");
        System.out.println(futureTask.isDone());
        System.out.println(futureTask.get());
    }
}

class ThreadTest implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("start thread2....");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end thread2....");
        return "end2";
    }
}
