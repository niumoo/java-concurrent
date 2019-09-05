package net.codingme.concurrent.chapter1;

/**
 * <p>
 * 1.11 ThreadLocal
 * ThreadLocal 的变量，会为每一个变量创建一个本地副本，
 * 多个线程操作这个变量的时候，其实都是自己内部的变量，
 * 所以互不影响
 * 
 * 缺陷：不能继承
 *
 * @Author niujinpeng
 * @Date 2019/9/4 15:15
 */
public class ThreadLocalDemo {

    private static ThreadLocal<String> local = new ThreadLocal();

    public static void main(String[] args) throws InterruptedException {
        // 创建线程 A
        Thread threadA = new Thread(() -> {
            local.set("Thread AA");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread A get Thread Local :" + local.get());

        });
        // 创建线程 B
        Thread threadB = new Thread(() -> {
            local.set("Thread BB");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread B get Thread Local :" + local.get());

        });
        // 启动线程
        threadA.start();
        threadB.start();
        Thread.sleep(3000);

        // ThreadLocal 不能继承
        local.set("Thread main mian ");
        // 创建线程 C
        Thread threadC = new Thread(() -> {
            System.out.println("Thread C get Thread Local :" + local.get());
        });
        threadC.start();
        System.out.println("2 Main thread get Thread Local :" + local.get());
    }

}
