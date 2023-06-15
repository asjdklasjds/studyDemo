package com.kmp;

/**
 * 字符串匹配（暴力匹配算法） 用于跟KMP算法作比较
 * @author yan
 */
public class ViolentMatch {
    public static void main(String[] args) {
        String str1 = "很骄傲手动滑稽爱神的箭暗示健康的骄傲里盛开的奥斯卡德令哈打老虎";
        String str2 = "暗示健康的骄傲里盛ss";
        int index = violentMatch(str1,str2);
        System.out.println("index = " + index);
    }

    /**
     * 暴力匹配算法
     * @param str1
     * @param str2
     * @return
     */
    private static int violentMatch(String str1, String str2){
        char[] s1 = str1.toCharArray(),s2 = str2.toCharArray();
        int s1Len = s1.length,s2Len = s2.length;
        int i=0,j=0;
        while (i < s1Len && j < s2Len){
            if (s1[i] == s2[j]){
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == s2Len){
            return i - j;
        }else {
            return -1;
        }
    }
}
