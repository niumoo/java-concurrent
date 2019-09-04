package net.codingme.concurrent.chapter1;

/**
 * <p>
 * 1.7
 * 线程中断
 * void interrupt() 中断线程
 * boolean interrupted() 检测当前线程是否被中断，发现当前线程被中断，
 * 会清除中断标志，且 interrupted 获取的是当前调用线程的中断标
 * 志，而不是调用 interrupted 方法的实例对象的中断标志。
 * boolean isInterrupted() 检测【当前】线程是否被中断
 *
 * @Author niujinpeng
 * @Date 2019/9/4 13:56
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread() + " Thread A");
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                System.out.println("Thread A interrupt");
            } finally {
                // cleanup
            }
        });

        threadA.start();
        Thread.sleep(1000);
        System.out.println("Main thread start interrupt thread");
        threadA.interrupt();
        System.out.println("Main thread end interrupt thread");
        Thread.sleep(1000);
        System.out.println("-----------------------------");

        Thread threadB = new Thread(() -> {
            for (;;) {
            }
        });
        threadB.start();
        threadB.interrupt();
        // 获取中断标志
        System.out.println("isInterrupted:" + threadB.isInterrupted());
        // 获取中断标志并重置
        System.out.println("interrupted:" + threadB.interrupted());
        // 获取中断标志并重置
        System.out.println("interrupted:" + Thread.interrupted());
        System.out.println("isInterrupted:" + threadB.isInterrupted());
        System.out.println("-----------------------------");

        Thread threadC = new Thread(() -> {
            // 判断是否中断并且重置中断状态
            while (!Thread.currentThread().interrupted()) {

            }
            System.out.println("Threadc isInterrupted:" + Thread.currentThread().isInterrupted());
        });

        threadC.start();
        threadC.interrupt();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("Main thread is over");

    }

}
