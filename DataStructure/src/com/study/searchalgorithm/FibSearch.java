package com.study.searchalgorithm;

import java.util.Arrays;

/**
 * 裴波那契（黄金分割查找）查找
 * TODO 有问题int[] arr = {1,2,3,4,5,6,7,8,89}; 使用 fibSearch(arr,89) 查询不到
 * @author yan
 */
public class FibSearch {
    static int arrSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,89};
        System.out.println(fibSearch(arr,5));
    }

    private static int[] fib(){
        int[] p = new int[arrSize];
        p[0] = 1;
        p[1] = 1;
        for (int i = 2; i < p.length; i++) {
            p[i] = p[i-1] + p[i-2];
        }
        return p;
    }

    private static int fibSearch(int[] a, int finalVal){
        int left = 0;
        int right = a.length - 1;
        int mid = 0;
        int fibIndex = 0;
        int fib[] = fib();

        //获取斐波那契函分割数值的下标
        while(right > fib[fibIndex]){
            fibIndex++;
        }
        //因为斐波那契数组的fibonacci[fibIndex]值可能大于arr数组的长度，，因此我们要使用Arrays类，
        //构造一个新的数组，并指向arr[]
        //不足得部分会使用0填充
        int temp[]=Arrays.copyOf(a,fib[fibIndex]);
        //实际上需求用arr最后的值来进行补充
        for(int i=right+1;i<fib[fibIndex];i++) {
            temp[i] = a[right];
        }

        //使用while循环，找到我们的数Value
        //只要这个条件满足 ，就可以找
        while(left<right) {
            //中间下标
            mid = left + fib[fibIndex - 1] - 1;
            if (finalVal > temp[mid]) {
                //要找的值大于中间值说明在数组的右边
                //fibonacci[fibIndex]=fibonacci[fibIndex-1]+fibonacci[fibIndex-2];
                //fibonacci[fibIndex-1] 为数组左边的值 fibonacci[fibIndex-2]为数组右边的值
                left = mid + 1;
                fibIndex -= 2;
            } else if (finalVal < temp[mid]) {
                //要找的数小于中间值，说明在数组的左边 fibIndex--；
                right = mid - 1;
                fibIndex--;
            } else {
                //注意：这里要进行判断返回时是哪个下标
                //如果mid<right 如果mid>right，返回right；
                if (mid < right) {
                    return mid;
                } else {
                    return right;
                }
            }
        }
        //如果没有找到则返回-1
        return -1;
    }

}
