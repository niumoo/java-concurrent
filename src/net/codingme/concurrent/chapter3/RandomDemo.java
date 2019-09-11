package net.codingme.concurrent.chapter3;

import java.util.Random;

/**
 * <p>
 * 3.1 随机数生成器
 *
 * Raddom 的缺点：next 方法的自旋缺陷，消耗性能。
 * 因此有了 ThreadLocalRandom
 * 
 * @see java.util.Random#next(int)
 * @Author niujinpeng
 * @Date 2019/9/10 11:30
 */
public class RandomDemo {

    public static void main(String[] args) {
        // 创建一个默认种子的随机数生成器
        Random random = new Random();
        // 输出10个 0-5 的随机数
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }
    }
}
