package net.codingme.concurrent.chapter1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 1.5
 * sleep() 可以让线程休眠指定时间，让出 CPU 使用权
 * sleep() 不会释放持有的资源锁
 * sleep() 期间被中断，会抛出 sleep interrupted
 *
 * @Author niujinpeng
 * @Date 2019/9/4 11:16
 */
public class ThreadSleep {
    /** 创建一个独占锁 */
    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        // 创建线程
        Thread threadA = new Thread(() -> {
            // 获取独占锁
            LOCK.lock();
            try {
                System.out.println("Thread A is in sleep");
                Thread.sleep(5000);
                System.out.println("Thread A is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        });
        // 创建线程
        Thread threadB = new Thread(() -> {
            // 获取独占锁
            LOCK.lock();
            try {
                System.out.println("Thread B is in sleep");
                Thread.sleep(5000);
                System.out.println("Thread B is in awaked");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        });

        // 启动线程
        threadA.start();
        threadB.start();
        threadB.interrupt();
    }

}
