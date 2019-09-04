package net.codingme.concurrent.chapter1;

/**
 * <p>
 * 1.4
 * 可以让线程等待的 join 方法
 * 线程 A 调用线程 B 的 join 方法时会被阻塞，
 * 这是如果线程 A 被 interrupt, 则会抛出异常
 * InterrutpedException
 * 
 *
 * @Author niujinpeng
 * @Date 2019/9/4 10:58
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        // 创建线程
        Thread threadA = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread A over");
        });

        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread B over");
        });

        // 启动线程
        threadA.start();
        threadB.start();

        System.out.println("Wait thread over");
        // 等待线程执行完毕，返回
        threadB.join();
        threadA.join();
        System.out.println("Main thread over");
    }
}
