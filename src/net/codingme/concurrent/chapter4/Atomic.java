package net.codingme.concurrent.chapter4;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * 4.1 原子变量的操作
 *
 * @Author niujinpeng
 * @Date 2019/9/11 14:11
 */
public class Atomic {

    /** 创建 long 类型原子计数器 */
    private static AtomicLong ATOMIC_LONG = new AtomicLong();
    /** 创建数据源 */
    private static Integer[] ARRAY_ONE = new Integer[] {0, 1, 2, 3, 0, 5, 6, 0, 56, 0};
    private static Integer[] ARRAY_TWO = new Integer[] {10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < ARRAY_ONE.length; i++) {
                    if (ARRAY_ONE[i] == 0) {
                        ATOMIC_LONG.incrementAndGet();
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < ARRAY_TWO.length; i++) {
                    if (ARRAY_TWO[i] == 0) {
                        ATOMIC_LONG.incrementAndGet();
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("ATOMIC_LONG Count 0: " + ATOMIC_LONG.get());
    }

}
