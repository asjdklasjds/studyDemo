package com.study.sortalgorithm;

import java.text.SimpleDateFormat;

public class CommonUtil {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");

    public static void paddingArr(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000000);
        }
    }
}
