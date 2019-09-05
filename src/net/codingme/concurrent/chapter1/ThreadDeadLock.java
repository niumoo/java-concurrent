package net.codingme.concurrent.chapter1;

/**
 * <p>
 * 1.9 死锁
 * 死锁的条件
 * 1. 互斥
 * 2. 请求并持有条件
 * 3. 不可剥夺条件
 * 4. 环形等待条件
 * 
 * 利用资源的有序持有，可以打破死锁，因为有序持续会打破
 * 死锁条件的 请求并持有条件和环形等待条件
 *
 * @Author niujinpeng
 * @Date 2019/9/4 14:32
 */
public class ThreadDeadLock {

    /** 创建资源 */
    private static Object resourceA = new Object();
    private static Object resourceB = new Object();

    public static void main(String[] args) {
        // 创建线程
        Thread threadA = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread() + " get ResourceA");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resourceB");
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + " get resourceB");
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (resourceB) {
                System.out.println(Thread.currentThread() + " get ResourceB");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resourceA");
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + " get resourceA");
                }
            }
        });
        threadA.start();
        threadB.start();
    }

}
