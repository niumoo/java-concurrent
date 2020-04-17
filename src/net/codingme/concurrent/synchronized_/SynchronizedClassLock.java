package net.codingme.concurrent.synchronized_;

/**
 * <p>
 * Synchronized 类锁
 * 
 * @author niujinpeng
 * @Date 2020/4/11 17:02
 */
public class SynchronizedClassLock implements Runnable {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new SynchronizedClassLock());
        Thread thread2 = new Thread(new SynchronizedClassLock());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("main thread end");
    }

    @Override
    public void run() {
        // 类锁，使用静态代码块
        synchronized (SynchronizedClassLock.class) {
            String name = Thread.currentThread().getName();
            System.out.println(name + ":thread cod start");
            method();
            System.out.println(name + ":thread cod end");
        }
    }

    /**
     * 类锁,静态方法上的锁是类锁
     */
    public static synchronized void method() {
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
