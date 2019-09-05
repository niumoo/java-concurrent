package net.codingme.concurrent.chapter1;

/**
 * <p>
 * 1.11.4 InheritableThreadLocal
 * 可以继承的 ThreadLocal，让子线程可以访问父线程中的变量值。
 * 场景：子线程获取用户信息，记录请求 ID 用于链路追踪等
 *
 * @Author niujinpeng
 * @Date 2019/9/4 16:33
 */
public class ThreadInheritableThreadLocal {

    public static InheritableThreadLocal<String> local = new InheritableThreadLocal<>();

    /**
     * println：Thread A get Local:Dog
     * 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        local.set("Dog");
        Thread threadA = new Thread((() -> {
            System.out.println("Thread A get Local:" + local.get());
        }));
        threadA.start();
        threadA.join();
    }

}
