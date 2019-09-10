package net.codingme.concurrent.chapter2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 2.12.2 公平锁与非公平锁
 *
 * 公平锁可以保证先到先得,非公平锁则根据 CPU 轮询。
 * 没有公平性要求时候应该尽量使用非公平锁,性能开销较小。
 *
 * @Author niujinpeng
 * @Date 2019/9/9 18:03
 */
public class LockReentrant {
    /** 公平锁 */
    private static ReentrantLock fairLock = new ReentrantLock(true);
    /** 非公平锁 */
    private static ReentrantLock unfairLock = new ReentrantLock(false);
    private static ReentrantLock unfairLock2 = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        // 公平锁
        Thread fairLock0 = new Thread(new ThreadLockFair(0));
        Thread fairLock1 = new Thread(new ThreadLockFair(1));
        Thread fairLock2 = new Thread(new ThreadLockFair(2));
        Thread fairLock3 = new Thread(new ThreadLockFair(3));
        fairLock0.start();
        fairLock1.start();
        fairLock2.start();
        fairLock3.start();
        fairLock0.join();
        fairLock1.join();
        fairLock2.join();
        fairLock3.join();
        System.out.println("-------------------------------------------");
        // 非公平锁
        Thread unFairLock0 = new Thread(new ThreadLockUnFair(0));
        Thread unFairLock1 = new Thread(new ThreadLockUnFair(1));
        Thread unFairLock2 = new Thread(new ThreadLockUnFair(2));
        Thread unFairLock3 = new Thread(new ThreadLockUnFair(3));
        unFairLock0.start();
        unFairLock1.start();
        unFairLock2.start();
        unFairLock3.start();
        unFairLock0.join();
        unFairLock1.join();
        unFairLock2.join();
        unFairLock3.join();

    }

    /**
     * 使用公平锁
     */
    public static class ThreadLockFair implements Runnable {
        private int count;

        ThreadLockFair(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            fairLock.lock();
            System.out.println(count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fairLock.unlock();
        }
    }

    /**
     * 使用非公平锁
     */
    public static class ThreadLockUnFair implements Runnable {
        private int count;

        ThreadLockUnFair(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            unfairLock.lock();
            System.out.println(count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            unfairLock.unlock();
        }
    }
}
