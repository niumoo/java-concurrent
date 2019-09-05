package net.codingme.concurrent.chapter1;

/**
 * <p>
 * 1.10 守护线程
 * 守护线程不影响 JVM 的退出
 * 只要有一个用户线程，JVM 就不会退出。
 *
 * 如果希望主线程结束后，JVM 退出，可以使用守护线程，
 * 如果需要子线程继续工作完毕，可以设置为用户线程。
 * 
 * 
 * @Author niujinpeng
 * @Date 2019/9/4 15:01
 */
public class ThreadDaemon {

    public static void main(String[] args) {
        // 创建线程
        Thread threadDemon = new Thread(() -> {
            for (;;) {
            }
        });
        // 设置为守护线程，运行结束会退出 JVM
        threadDemon.setDaemon(true);
        threadDemon.start();
        System.out.println("Main thread is over");
    }
}
