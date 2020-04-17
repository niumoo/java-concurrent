package net.codingme.concurrent.synchronized_;

/**
 * <p>
 * Synchronized 在抛出异常后自动释放锁
 *
 * @author niujinpeng
 * @Date 2020/4/11 18:22
 */
public class SynchronizedException implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        // 方法锁是对象锁，下面是同一个对象，所以锁会生效
        SynchronizedException objectLock = new SynchronizedException();
        Thread thread1 = new Thread(objectLock);
        Thread thread2 = new Thread(objectLock);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("main thread end");
    }

    @Override
    public void run() {
        try {
            method();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void method() throws Exception {
        String name = Thread.currentThread().getName();
        System.out.println(name + ":thread start");
        try {
            Thread.sleep(3000);
            throw new Exception();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + ":thread end");
    }
}
