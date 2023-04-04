package com.study.stack;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayStack stack = new ArrayStack(5);
        boolean flag = true;
        while (flag){
            System.out.println("s:show\tp:push\tpop:pop：");
            String inVal = scanner.next();
            try{
                switch (inVal){
                    case "p":{
                        System.out.print("请输入值：");
                        int val = scanner.nextInt();
                        stack.push(val);
                        break;
                    }
                    case "s":{
                        stack.showStack();
                        break;
                    }
                    case "pop":{
                        System.out.println(stack.pop());
                        break;
                    }
                    case "e":{
                        scanner.close();
                        flag = false;
                        break;
                    }
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

}

class ArrayStack{

    private int size;

    private int top = -1;

    private int[] stack;

    public int[] getStack() {
        return stack;
    }

    public int getTop() {
        return top;
    }

    public ArrayStack(int size){
        this.size = size;
        this.stack = new int[this.size];
    }

    public boolean isFull(){
        return top == size - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    // 往栈中压入数据
    public void push(int val){
        if (isFull()) throw new RuntimeException("栈满！");
        top++;
        stack[top] = val;
    }

    // 从栈中取数据
    public int pop(){
        if (isEmpty()) throw new RuntimeException("栈空！");
        return stack[top--];
    }

    public void showStack(){
        if (isEmpty()) throw new RuntimeException("栈空！");
        for (int i = top; i > -1; i--) {
            System.out.println(stack[i]);
        }
    }

}
