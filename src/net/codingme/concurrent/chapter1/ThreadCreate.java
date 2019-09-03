package net.codingme.concurrent.chapter1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * <p>
 * 1.2
 * 创建线程的方式
 *
 * @Author niujinpeng
 * @Date 2019/9/3 22:06
 */
public class ThreadCreate {

    public static void main(String[] args) {
        // 方式1
        CreateThread1 thread1 = new CreateThread1();
        thread1.start();

        // 方式2
        CreateThread2 thread2 = new CreateThread2();
        new Thread(thread2).start();
        new Thread(thread2).start();

        // 方式3
        FutureTask futureTask = new FutureTask<>(new CreateThread3());
        // 启动线程
        new Thread(futureTask).start();
        try {
            // 等待任务执行完毕
            String result = (String)futureTask.get();
            // 获取返回结果
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 继承 Thread <br/>
     * 具有 Java 单继承的弊端，不能获取返回值
     */
    public static class CreateThread1 extends Thread {
        @Override
        public void run() {
            System.out.println("Create a thread1....");
        }
    }

    /**
     * 实现 Runnable 方法 <br/>
     * 不能获取返回值
     */
    public static class CreateThread2 implements Runnable {
        @Override
        public void run() {
            System.out.println("Create a thread2....");
        }
    }

    /**
     * 实现 Callable <br/>
     * 可以获取到线程的返回值
     */
    public static class CreateThread3 implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "Create a thread3....";
        }
    }

}
