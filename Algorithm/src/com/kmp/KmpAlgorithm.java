package com.kmp;

import java.util.Arrays;

/**
 * KMP算法
 * @author yan
 */
public class KmpAlgorithm {
    public static void main(String[] args) {
        String s1 = "SJKASDJKSAAABADSHJFD ASDHJH ，ASJKajskA";
        String s2 = "SJKASDJKSA";

        int[] ints = kmpNext(s2);
        System.out.println(Arrays.toString(ints));
        System.out.println(kmpSearch(s1,s2));
    }


    /**
     * kmp查找算法
     * @param s1 原始字符串
     * @param s2 查找的字符串
     * @return 返回找到的index 没找到返回-1
     */
    private static int kmpSearch(String s1, String s2){
        int[] next = kmpNext(s2);
        for (int i = 0,j = 0; i < s1.length(); i++) {
            while (j > 0 && s1.charAt(i) != s2.charAt(j))
                j = next[j-1];
            if (s1.charAt(i) == s2.charAt(j))
                j++;
            if (j == s2.length())
                return i - j + 1;
        }
        return -1;
    }

    /**
     * 得到需要检索字符串的部分匹配值表
     * @param s 需要匹配的值
     * @return 部分匹配值表
     */
    private static int[] kmpNext(String s){
        int[] next = new int[s.length()];
        // 如果传入的字符串长度小于等于2 则next[0] = 0;
        next[0] = 0;
        for (int i = 1,j = 0; i < s.length(); i++) {

            // kmp算法核心
            while (j > 0 && s.charAt(i) != s.charAt(j))
                // 这里相当于是一个回溯
                j = next[j-1];

            if (s.charAt(i) == s.charAt(j))
                j++;
            next[i] = j;
        }
        return next;
    }
}
