package net.codingme.concurrent.chapter2;

/**
 * <p>
 * 2.11 伪共享
 *
 * 共享可以提高运行速度，但是伪共享会让程序运行的更慢
 * 
 * 可以使用 Contended 注解来解决伪共享
 * 
 * @sun.misc.Contended
 * @sun.misc.Contended("tlr")
 *
 * @Author niujinpeng
 * @Date 2019/9/9 17:25
 */
public class FalseSharing {

    private static int LINE_NUM = 1024;
    private static int COLUM_NUM = 1024;

    public static void main(String[] args) {
        share();
        falseShare();
    }

    /**
     * 没有伪共享
     */
    public static void share() {
        long[][] array = new long[LINE_NUM][COLUM_NUM];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUM_NUM; j++) {
                array[i][j] = i * 7 + j * 3;
            }
        }
        long endTime = System.currentTimeMillis();
        long cacheTime = (endTime - startTime);
        System.out.println("share cache time:" + cacheTime);
    }

    /**
     * 发生伪共享
     */
    public static void falseShare() {
        long[][] array = new long[LINE_NUM][COLUM_NUM];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUM_NUM; j++) {
                array[j][i] = i * 7 + j * 3;
            }
        }
        long endTime = System.currentTimeMillis();
        long cacheTime = (endTime - startTime);
        System.out.println("FalseShare cache time:" + cacheTime);
    }

}
