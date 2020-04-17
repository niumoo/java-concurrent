package net.codingme.concurrent.chapter6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 *
 * @author niujinpeng
 * @Date 2020/3/31 22:42
 */
public class LockSuppertTest {

    public static void main(String[] args) throws InterruptedException {
        // 测试 1 如果调用 park 方法的线程已经拿到了与 LockSupport 关联的许可证，则调用 LockSupport.part
        // 会马上返回，否则调用线程会被阻塞
        // System.out.println("begin park");
        // LockSupport.park();
        // System.out.println("end park");

        // 测试 2
        // System.out.println("begin park");
        // 让当前线程持有许可证
        // LockSupport.unpark(Thread.currentThread());
        // 只有许可证的线程调用 park 会立即返回
        // System.out.println("end park");

        // 测试 3
        // Thread thread = new Thread(() -> {
        // System.out.println("thread begin park");
        // LockSupport.park();;
        // System.out.println("thread end park");
        // });
        // thread.start();
        // Thread.sleep(1000);
        // System.out.println("main thread start unpark");
        // LockSupport.unpark(thread);
        LockSuppertTest lockSuppertTest = new LockSuppertTest();
        lockSuppertTest.test();
    }

    /**
     * park 方法传入 this 可以监控到被阻塞的类
     */
    public void test() {
        LockSupport.park(this);
    }
}
