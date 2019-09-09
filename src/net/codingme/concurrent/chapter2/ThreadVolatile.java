package net.codingme.concurrent.chapter2;

/**
 * <p>
 * 2.10 指令重排序
 * 
 * 注释的 1,2,3,4 步骤中的 3,4 没有顺序，所以运行时候可能 4 先运行
 * 这时候运行就会出现异常结果
 * 
 * 使用 volatile 修饰 num 变量可以禁用指令重排序
 * 
 *
 * @Author niujinpeng
 * @Date 2019/9/5 18:36
 */
public class ThreadVolatile {

    private static int num = 0;
    private static volatile boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        readThread.start();
        WriteThread wt = new WriteThread();
        wt.start();
        Thread.sleep(1);
        readThread.interrupt();
        System.out.println("Main thread exit");
    }

    static class ReadThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                // 1
                if (ready) {
                    // 2
                    System.out.println(num + num);
                }
            }
        }
    }

    static class WriteThread extends Thread {

        @Override
        public void run() {
            // 3
            num = 2;
            // 4
            ready = true;
            System.out.println("Write Thread set over");
        }
    }

}
