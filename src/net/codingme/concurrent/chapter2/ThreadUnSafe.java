package net.codingme.concurrent.chapter2;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * <p>
 * 2.9.2 使用 Unsafe 类
 * 
 *
 * @Author niujinpeng
 * @Date 2019/9/5 15:52
 */
public class ThreadUnSafe {
    /** 获取 unsafe 实例 */
    static Unsafe unsafe;

    /** 记录变量 state 在类 TestUnSafe钟的偏移值 */
    static long stateOffset = 0;

    /** 变量 */
    private volatile long state = 0;

    static {
        try {
            // 使用反射获取 Unsafe 的成员遍历 theUnsafe
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            // 设置为可存取
            field.setAccessible(true);
            // 获取该变量的值
            unsafe = (Unsafe)field.get(null);

            // 获取 state 变量在类 TestUnsafe中的偏移值
            stateOffset = unsafe.objectFieldOffset(ThreadUnSafe.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 创建示例，并且设置 state 值为 1
        ThreadUnSafe threadUnSafe = new ThreadUnSafe();
        boolean success = unsafe.compareAndSwapLong(threadUnSafe, stateOffset, 0, 1);
        System.out.println(success);
    }

}
