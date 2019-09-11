package net.codingme.concurrent.chapter3;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 * 3.2 ThreadLocalRandom
 * 为了弥补 Random 下的性能缺陷而诞生。
 *
 * @Author niujinpeng
 * @Date 2019/9/10 14:26
 */
public class ThreadLocalRandomDemo {

    public static void main(String[] args) {
        // 获取一个随机数生成器
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // 输出 10 个 0-5(包含0不包含5) 的随机数
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(5));
        }
    }

}
