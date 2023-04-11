package com.study.recurrence;

/**
 * 八皇后问题
 * 回溯算法
 * @author yan
 */
public class Queens8 {
    // 多少个皇后
    int max = 8;
    // 存储八皇后摆法
    int[] way = new int[max];
    static int count = 0;
    static int judgeCount = 0;
    public static void main(String[] args) {
        new Queens8().check(0);
        System.out.printf("一共有 %d 次解法\n",count);
        System.out.printf("一同判断了(judge) %d 次\n",judgeCount);
    }


    private void check(int n){
        if (n == max){
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            way[n] = i;
            // 判断摆法是否成立  如不成立则n+1继续执行 check（n+1） = 当前行的列往后移
            if (judge(n)){
                check(n+1);
            }
            // 不成立则继续执行
        }
    }

    /**
     * 判断 第n个皇后 与之前的皇后是否冲突
     * @param n 第n个皇后
     * @return true：没有冲突  false：有冲突
     */
    private boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++) {
            /**
             * way[i] == way[n] 判断 是否在同一行并且同一列 在同一个位置
             * Math.abs(n-i) == Math.abs(way[n] - way[i] 判断是否在同一个斜线
             *  比如 n 从0开始  现在 n = 1;
             *       way[1] = 0 即 way[i] == way[n] 条件成立
             *      那么 Math.abs(1-0) = 1
             *      Math.abs(（way[1]=1） - （way[0] = 0）) = 1
             *      因此条件成立 及在同一斜线 返回false
             */
            if (way[i] == way[n] || Math.abs(n-i) == Math.abs(way[n] - way[i])) return false;
        }
        return true;
    }

    /**
     * 输出way中的元素
     */
    private void print(){
        count++;
        for (int i : way) {
            System.out.print(i+"\t");
        }
        System.out.println();
    }
}
