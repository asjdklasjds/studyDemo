package com.study.searchalgorithm;

/**
 * 插值查找算法
 * 插值查找算法与二分查找一样  也是需要需要查找的数组是有序的
 * @author yan
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {12,34,54,65,65,65,68,69,77,78};
        System.out.println(insertValueSearchOne(arr,0,arr.length-1,11));
    }

    /**
     * 插值查找 查找finalVal出现的下标
     * 只查一个 不查重复的
     * 跟二分查找类似
     * 只是取mid值的算法不一样
     * @param arr
     * @param left
     * @param right
     * @param finalVal
     */
    private static int insertValueSearchOne(int[] arr, int left, int right, int finalVal){
        System.out.println("====");
        // finalVal < arr[0] || finalVal > arr[arr.length-1] 这两个条件必须加上
        // 如果finalVal 大于arr的最大值或者小于最小值时避免索引越界的异常ArrayIndexOutOfBoundsException
        if (left > right || finalVal < arr[0] || finalVal > arr[arr.length-1]) return -1;
        int mid = left + (right - left) * (finalVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (midVal > finalVal) return insertValueSearchOne(arr,mid+1,right,finalVal);
        else if (midVal < finalVal) return insertValueSearchOne(arr,left,mid-1,finalVal);
        else return mid;
    }
}
