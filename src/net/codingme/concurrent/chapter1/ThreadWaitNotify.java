package net.codingme.concurrent.chapter1;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <p>
 * 1.3
 * 线程的通知和等待 <br/>
 * 线程调用一个共享变量的 wait() 方法时，线程会被阻塞，释放资源锁<br/>
 * <p>
 * 直到
 * 1. 其他线程调用共享对象的 notify() 或者 notifyAll() <br/>
 * 2. 其他线程调用了该线程的 interrupt() 方法抛出异常返回 <br/>
 * <p>
 * 为了防止虚假唤醒，可以循环判断唤醒条件
 *
 * @Author niujinpeng
 * @Date 2019/9/3 22:17
 */
public class ThreadWaitNotify {

    public static void main(String[] args) throws InterruptedException {
        Producer producer = new Producer();
        Consume consume = new Consume();
        new Thread(consume).start();
        new Thread(consume).start();
        new Thread(producer).start();
        new Thread(producer).start();
    }

    private static ConcurrentLinkedQueue QUEUE = new ConcurrentLinkedQueue();
    private static Integer MAX_SIZE = 10;

    /**
     * 生产者
     */
    public static class Producer implements Runnable {

        @Override
        public void run() {
            synchronized (QUEUE) {
                // 队列已满，等待
                while (QUEUE.size() == MAX_SIZE) {
                    try {
                        System.out.println("生产者队列满");
                        QUEUE.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                QUEUE.add("car");
                System.out.println("生产者生产了一个car");
                QUEUE.notifyAll();
            }
        }
    }

    /**
     * 消费者
     */
    public static class Consume implements Runnable {

        @Override
        public void run() {
            synchronized (QUEUE) {
                // 队列空，等待
                while (QUEUE.size() == 0) {
                    try {
                        System.out.println("消费者等待数据");
                        QUEUE.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Object object = QUEUE.poll();
                System.out.println("消费者得到可以消费的数据" + object);
                QUEUE.notifyAll();
            }
        }
    }
}
