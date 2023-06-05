package com.dynamicalgorithm;

/**
 * 动态规划算法（背包问题）
 * @author yan
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int w[] = {1,4,3,1};  // 记录商品的重量
        int val[] = {1500,3000,2000,9999}; // 记录商品的价格
        int m = 4; // 背包可以装的重量
        int n = val.length;  // 物品的个数

        // 创建二维数组
        int[][] v = new int[n+1][m+1];
        // 初始化第一行为第一列，这里可以省略 因为默认第一行第一列就是0

        // 为了记录放入商品的情况，定义一个二维数组
        int[][] path = new int[n+1][m+1];

        // 根据公式来动态规划处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++){
                // 公式
                if (w[i-1] > j){
                    v[i][j] = v[i-1][j];
                }else {
                    //v[i][j] = Math.max(v[i-1][j],val[i-1] + v[i-1][j-w[i-1]]);
                    // 为了记录商品存放到背包的情况， 不能直接使用上面的公式，需要使用if-else来体现公式
                    if (v[i-1][j] < val[i-1] + v[i-1][j-w[i-1]]){
                        v[i][j] = val[i-1] + v[i-1][j-w[i-1]];
                        // 把当前情况记录到 path
                        path[i][j] = 1;
                    }
                }
            }
        }

        // 查看 v 的情况
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        // 最后输出path 看那些物品装入了背包
        int i = path.length - 1,
            j = path[0].length - 1;
        while (i > 0 && j > 0){ // 从path的最后开始找
            if (path[i][j] == 1){
                System.out.printf("第%d个商品放入了背包\n",i);
                j -= w[i-1];
            }
            i--;
        }
    }
}
