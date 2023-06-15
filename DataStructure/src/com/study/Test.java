package com.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        Test test = new Test();
//        test.uniquePaths(6,8);
//        List<String> list = new ArrayList<>();
//        list.add("12");
//        list.stream().forEach(e -> {
//            try {
//                test.climbStairs(1);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });
        test.doubleNumber();
    }

    public int climbStairs(int n) throws Exception {
        if (n <= 2) return n;
        int a = 1, b = 2,temp;
        for(int i = 3; i <= n; i++){
            temp = a+b;
            a = b;
            b = temp;
        }
        return b;
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for(int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0]; // 边界：第一列
        for(int j = 1; j < n; j++) dp[0][j] = dp[0][ j - 1]; // 边界：第一行
        for (int i = 0; i < m; i++){
            System.out.println(Arrays.toString(dp[i]));
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        System.out.println("=================");
        for (int i = 0; i < m; i++){
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[m - 1][n - 1];
    }

    public void doubleNumber(){
        for (int i = 1;; i++) {
            String s = String.valueOf(i);
            String val = s.substring(s.length()-1) + s.substring(0,s.length()-1);
            if (i == (Integer.valueOf(val) * 2)){
                System.out.println(Integer.valueOf(val));
                System.out.println(i);
            }
        }
    }
}
