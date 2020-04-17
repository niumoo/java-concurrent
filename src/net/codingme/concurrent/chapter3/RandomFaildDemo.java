package net.codingme.concurrent.chapter3;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 *
 * @author niujinpeng
 * @Date 2020/3/10 7:58
 */
public class RandomFaildDemo {

    private static Random random = new Random();
//    private static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                int anInt = random.nextInt(10);
                System.out.println(anInt);
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                int anInt = random.nextInt(10);
                System.out.println(anInt);
            }
        });

        long start = System.currentTimeMillis();
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        long end = System.currentTimeMillis();
        System.out.println((end - start)+"ms");
    }
}
