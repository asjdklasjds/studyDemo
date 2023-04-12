package com.study.sortAlgorithm;

public class CommonUtil {

    public static void paddingArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000000);
        }
    }
}
