package net.codingme.concurrent.chapter2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 2.12.4 可重入锁
 * synchronized 可重入
 * reentrantLock 可重入
 *
 * @Author niujinpeng
 * @Date 2019/9/10 10:59
 */
public class LockReentrantDemo {

    public static void main(String[] args) {
        methodA();
        methodC();
    }

    public static synchronized void methodA() {
        System.out.println("Mehtod A");
        methodB();
    }

    public static synchronized void methodB() {
        System.out.println("Mehtod B");
    }

    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void methodC() {
        reentrantLock.lock();
        System.out.println("Mehtod C");
        methodD();
        reentrantLock.unlock();
    }

    public static void methodD() {
        reentrantLock.lock();
        System.out.println("Mehtod D");
        reentrantLock.unlock();
    }
}
