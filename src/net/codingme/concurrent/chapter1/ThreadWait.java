package net.codingme.concurrent.chapter1;

/**
 * <p>
 * wait() 只会释放当前共享变量上的锁
 *
 * @Author niujinpeng
 * @Date 2019/9/4 10:15
 */
public class ThreadWait {

    /*** 创建资源 */
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 创建线程 A
        Thread threadA = new Thread(() -> {
            try {
                // 获取 resourceA 共享资源的监视器
                synchronized (resourceA) {
                    System.out.println("ThreadA get resourceA lock");
                    // 获取 resourceB 共享资源的监视器
                    synchronized (resourceB) {
                        System.out.println("ThreadA get resourceB lock");
                        // 线程 A 阻塞，释放获取到的 resourceA 的锁
                        System.out.println("ThreadA release reourceA lock");
                        resourceA.wait();
                    }
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
                    System.out.println("ThreadB try get resourceB lock");
                    // 获取 resourceB 共享资源的监视器
                    synchronized (resourceB) {
                        System.out.println("ThreadB get resourceB lock");
                        // 线程 A 阻塞，释放获取到的 resourceA 的锁
                        System.out.println("ThreadB release resourceA lock");
                        resourceA.wait();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 启动线程
        threadA.start();
        threadB.start();

        // 等待两个线程结束
        threadA.join();
        threadB.join();
        System.out.println("main thread over");
    }
}
