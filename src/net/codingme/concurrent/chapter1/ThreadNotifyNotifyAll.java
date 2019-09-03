package net.codingme.concurrent.chapter1;

/**
 * <p>
 * 线程的唤醒，notify() 和 notifyAll() 区别
 * 调用 notify() 之后放入 wait() 的线程不会被
 * 这次 notify() 唤醒
 *
 * @Author niujinpeng
 * @Date 2019/9/4 10:41
 */
public class ThreadNotifyNotifyAll {

    /*** 创建资源 */
    private static volatile Object resourceA = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 创建线程 A
        Thread threadA = new Thread(() -> {
            try {
                // 获取 resourceA 共享资源的监视器
                synchronized (resourceA) {
                    System.out.println("ThreadA get resourceA lock");
                    resourceA.wait();
                    System.out.println("ThreadA release reourceA lock");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 创建线程 B
        Thread threadB = new Thread(() -> {
            try {
                // 休眠 1s
                Thread.sleep(1000);
                // 获取 resourceA 共享资源的监视器
                synchronized (resourceA) {
                    System.out.println("ThreadB get resourceA lock");
                    resourceA.wait();
                    System.out.println("ThreadB release resourceA lock");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 启动线程
        threadA.start();
        threadB.start();

        Thread.sleep(1000);
        synchronized (resourceA) {
            // System.out.println("resourceA notify");
            // resourceA.notify();
            System.out.println("resourceA notifyAll");
            resourceA.notifyAll();
        }

        // 等待两个线程结束
        threadA.join();
        threadB.join();

        System.out.println("main thread over");
    }
}
