package net.codingme.concurrent.chapter2;

/**
 * <p>
 * 2.7 原子性操作
 * 不安全的累加
 * 
 * 若要保证内存可见性，可以在 getCount()
 * 和 inc() 方法上添加 synchronized 用来
 * 保证内存可见性
 *
 * @Author niujinpeng
 * @Date 2019/9/5 15:34
 */
public class ThreadNotSaceCount {
    private Long value;

    public long getCount() {
        return value;
    }

    public void inc() {
        ++value;
    }
}
