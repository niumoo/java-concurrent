package net.codingme.concurrent.synchronized_;

/**
 * <p>
 * 对象锁
 *
 * @author niujinpeng
 * @Date 2020/4/11 16:47
 */
public class SynchronizedObjectLock implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        // 方法锁是对象锁，下面是同一个对象，所以锁会生效
        // SynchronizedObjectLock objectLock = new SynchronizedObjectLock();
        // Thread thread1 = new Thread(objectLock);
        // Thread thread2 = new Thread(objectLock);
        // 下面是两个对于两个不同的对象，所以锁不会生效。
        Thread thread1 = new Thread(new SynchronizedObjectLock());
        Thread thread2 = new Thread(new SynchronizedObjectLock());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("main thread end");
    }

    @Override
    public synchronized void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + ":thread start");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + ":thread end");
    }
}
