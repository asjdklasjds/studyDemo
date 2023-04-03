package com.study.stack;

import java.util.Scanner;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedListStack<String> linkedListStack = new LinkedListStack<>();
        boolean flag = true;
        while (flag) {
            System.out.println("s:show\tp:push\tpop:pop：");
            String inVal = scanner.next();
            try {
                switch (inVal) {
                    case "p": {
                        System.out.print("请输入值：");
                        String val = scanner.next();
                        Node<String> node = new Node<>(val);
                        linkedListStack.push(node);
                        break;
                    }
                    case "s": {
                        linkedListStack.showLinkedListStack();
                        break;
                    }
                    case "pop": {
                        System.out.println(linkedListStack.pop().val);
                        break;
                    }
                    case "e": {
                        scanner.close();
                        flag = false;
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

class LinkedListStack<T>{

    Node<T> first;

    int top = -1;

    public boolean isEmpty(){
        return top == -1;
    }

    public void push(Node<T> val){
        top ++;
        if (first == null) first = val;
        else {
            Node<T> temp = first;
            while (temp.next != null) temp = temp.next;
            temp.next = val;
        }
    }

    public Node<T> pop(){
        if (isEmpty()) throw new RuntimeException("Stack is Empty");
        Node<T> val = get();
        top--;
        return val;
    }

    public void showLinkedListStack(){
        if (isEmpty()) throw new RuntimeException("Stack is Empty");
        for (int i = top; i > -1; i--) {
            System.out.println(get(i).val);
        }
    }

    // 根据下标获取指定的节点
    private Node<T> get(int index){
        Node<T> temp = first;
        for (int i = 0; i < index; i++)
            temp = temp.next;
        return temp;
    }

    // 获取链表最后一个元素出栈
    private Node<T> get(){
        Node<T> temp = first;
        for (int i = 0; i < top-1; i++)
            temp = temp.next;
        Node<T> retVal = null;
        if (top == 0){
            retVal = temp;
            temp = null;
        }else {
            retVal = temp.next;
            temp.next = null;
        }
        return retVal;
    }

}

class Node<T>{
    T val;

    Node<T> next;

    public Node(T val){
        this.val = val;
    }

}
