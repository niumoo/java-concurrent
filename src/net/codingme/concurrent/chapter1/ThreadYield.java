package net.codingme.concurrent.chapter1;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * <p>
 * 1.6
 * 线程礼让
 * 让出线程的CPU使用权，然后处于就绪状态，等待
 * 下一次调度执行
 *
 * @Author niujinpeng
 * @Date 2019/9/4 11:22
 */
public class ThreadYield {
    public static void main(String[] args) {
        Thread threadA = new Thread(new YieldThread());
        Thread threadB = new Thread(new YieldThread());
        Thread threadC = new Thread(new YieldThread());
        Thread threadD = new Thread(new YieldThread());
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }

    public static class YieldThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 5; i++) {
                if (i % 5 == 0) {
                    System.out.println(Thread.currentThread() + " yield cpu");
                    // 当前线程让出 CPU 使用权，放弃时间片，进行下一轮的调度
                    Thread.yield();
                }
            }
            System.out.println(Thread.currentThread() + " is over");
        }
    }
}
