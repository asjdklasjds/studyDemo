package com.study.tree;

import com.study.sortalgorithm.CommonUtil;

import java.util.Arrays;
import java.util.Date;

/**
 * 堆排序（树的实际使用）
 * 时间复杂度为 O(nlogn) 线性对数阶
 * @author yan
 */
public class HeapSort {
    static int arrSize = 8000000;
    public static void main(String[] args) {
//        int arr[] = {4,6,8,5,9};
        int arr[]  = new int[arrSize];
        CommonUtil.paddingArr(arr);
        System.out.println(CommonUtil.sdf.format(new Date()));
        heapSort(arr);
        System.out.println(CommonUtil.sdf.format(new Date()));
//        System.out.println(Arrays.toString(arr));
    }


    public static void heapSort(int[] arr){
//        int i = arr.length / 2 - 1;
//        adjustHeap(arr,i, arr.length);
//        System.out.println("第一次调整大顶堆 ： " + Arrays.toString(arr)); //4, 9, 8, 5, 6
//        adjustHeap(arr,(i-1), (arr.length-1));
//        System.out.println("第二次调整大顶堆 ： " + Arrays.toString(arr)); //9, 4, 8, 5, 6

        // 将整个数组使用adjustHeap调整为大顶堆
        for (int j = arr.length / 2 - 1; j > -1 ; j--) {
            adjustHeap(arr,j,arr.length);
        }
        int temp;
        // 将大顶堆的数放到 数组的最后 然后继续使用 adjustHeap 进行调整
        /**
         * 将堆顶元素与末尾元素交换，将最大元素 “沉” 到数组末端
         * 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素 反复执行调整
         */
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            // 这里 i 给的 0 是因为 前面已经从左至右 从下到上 整体调整过一次了
            adjustHeap(arr,0,i);
        }
    }

    /**
     * 调整 i 为父节点的树为大顶堆
     * @param arr 待调整的数组
     * @param i 以 i 为父节点的树
     * @param len 表示对多少个元素进行调整 len 是在逐步减少的
     */
    public static void adjustHeap(int arr[],int i, int len){
        // 得到当前节点的值
        int temp = arr[i];
        for (int j = i*2+1; j < len; j=j*2+1) {
            // 条件成立说明 左子节点的值小于右子节点的值
            if (j+1<len && arr[j] < arr[j+1])
                j++;
            if (arr[j] > temp){ // 如果子节点大于父节点
                arr[i] = arr[j]; // 将较大的值赋给当前节点
                i = j; // i 指向 j 继续循环比较
            }else {
                break;
            }
        }
        // 交换数据
        arr[i] = temp;
    }
}
