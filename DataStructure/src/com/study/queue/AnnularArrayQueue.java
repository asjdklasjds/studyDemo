package com.study.queue;

import java.util.Scanner;

public class AnnularArrayQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnnularQueue queue = new AnnularQueue(3);
        int chat = ' ';
        boolean flag = true;
        while (flag){
            try{
                System.out.print("a:add\ts:showAll\tg:get\t 其他:exit:\t");
                chat = scanner.next().charAt(0);
                switch (chat){
                    case 'a':
                        int res = scanner.nextInt();
                        queue.addQueue(res);
                        break;
                    case 's':
                        queue.printQueue();
                        break;
                    case 'g':
                        System.out.println("读取Queue的值 : " + queue.getQueueVal());
                        break;
                    default:
                        flag = false;
                        break;
                }
            }catch (ArrayQueueDemoException e){
                System.out.println(e.getMessage());
            }
        }
    }
}

class AnnularQueue{

    // 队列头
    private int head;

    // 队列尾
    private int tail;

    // 队列长度
    private int maxSize;

    private int[] annular;

    // 初始化队列
    public AnnularQueue(int maxArraySize){
        maxSize = maxArraySize + 1;
        annular = new int[maxSize];
    }

    // 判断队列是否满了
    public boolean isFull(){
        return (tail + 1) % maxSize == head;
    }

    // 判断队列是否为 空
    public boolean isEmpty(){
        return head == tail;
    }

    // 往队列中添加数据
    public void addQueue(int n) {
        if (isFull())
            throw new ArrayQueueDemoException("队列已满！");
        annular[tail] = n;
        tail = (tail + 1) % maxSize;
    }

    // 获取队列中的数据
    public int getQueueVal() {
        if (isEmpty())
            throw new ArrayQueueDemoException("队列为空！");
        int val = annular[head];
        head = (head + 1) % maxSize;
        return val;
    }

    // 打印队列中的所有数据
    public void printQueue(){
        if (isEmpty())
            throw new ArrayQueueDemoException("队列为空！");
        System.out.print("QueueDemo.printQueue:\t");
        for(int i = head; i < head + getArrayCount(); i++){
            System.out.printf("%d:%d\t",i % maxSize,annular[i % maxSize]);
        }
        System.out.println();
    }

    // 获取队列中所有的有效值
    public int getArrayCount(){
        return (tail + maxSize - head) % maxSize;
    }


}
