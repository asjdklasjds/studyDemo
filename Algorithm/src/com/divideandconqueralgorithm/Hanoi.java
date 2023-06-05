package com.divideandconqueralgorithm;

/**
 * 分治算法 （汉罗塔案例）
 * @author yan
 */
public class Hanoi {
    public static void main(String[] args) {
        hanoi(5,'A','B','C');
    }

    /**
     * 汉罗塔问题
     * @param num 有几张汉洛塔
     * @param a A柱子
     * @param b B柱子
     * @param c C柱子
     */
    static int i = 0;
    private static void hanoi(int num, char a, char b, char c){
        if (num == 1){
            System.out.println("序号: "+(++i)+"  第" + num + "块从 " + a + " -> " + c);
        } else {
            // 如果有num >= 2 的情况下  我们总可以看做是两个部分  最下面是一个部分  上面的所有是一个部分
            // 1、先将上面的部分移动到b A-B 移动过程中会使用到 C
            hanoi(num - 1, a, c, b);
            System.out.println("序号: "+(++i)+"  第" + num + "块从 " + a + " -> " + c);
            // 2、将b上面部分的盘移动到 c B-C 移动过程中会使用到 A
            hanoi(num - 1, b, a, c);
        }
    }
}
