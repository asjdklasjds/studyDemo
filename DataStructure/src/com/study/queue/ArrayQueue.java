package com.study.queue;

import java.util.Scanner;

/**
 * 使用数组实现队列 不能复用！
 * @author yan
 */
public class ArrayQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QueueDemo queue = new QueueDemo(3);
        int chat = ' ';
        boolean flag = true;
        while (flag){
            try{
                System.out.print("a:add\ts:showAll\tg:get\t 其他:exit \t:");
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

/**
 * 使用数组模拟队列
 */
class QueueDemo{

    // 队列长度
    private int maxSize;

    // 队列头指针
    private int head = -1;

    // 队列尾部指针
    private int tail = -1;

    private int[] queue;

    public QueueDemo(int n){
        this.maxSize = n;
        queue = new int[maxSize];
    }

    public boolean isFull(){
        return tail == maxSize-1;
    }

    public boolean isEmpty(){
        return tail == head;
    }

    public void addQueue(int n) {
        if (isFull())
            throw new ArrayQueueDemoException("队列已满！");
        queue[++tail] = n;
    }

    public int getQueueVal() {
        if (isEmpty())
            throw new ArrayQueueDemoException("队列为空！");
        return queue[++head];
    }

    public void printQueue(){
        if (isEmpty())
            throw new ArrayQueueDemoException("队列为空！");
        System.out.print("QueueDemo.printQueue:\t");
        for(int item:queue){
            System.out.printf("%d\t",item);
        }
        System.out.println();
    }
}

class ArrayQueueDemoException extends RuntimeException {

    public ArrayQueueDemoException(String msg){
        super(msg);
    }
}
