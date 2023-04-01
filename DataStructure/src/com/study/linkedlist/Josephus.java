package com.study.linkedlist;

import java.util.Objects;

public class Josephus {
    public static void main(String[] args) {
        AnnularSingeLinkedList annularSingeLinkedList = new AnnularSingeLinkedList(10);
        annularSingeLinkedList.showAll();
    }
}

class AnnularSingeLinkedList{
    private Boy first;

    public AnnularSingeLinkedList(int annularSize){
        Boy temp = null;
        for (int i = 1; i <= annularSize; i++) {
            if (i == 1){
                first = new Boy(i);
                temp = first;
            }else {
                temp.setNext(new Boy(i));
                temp = temp.getNext();
            }
            temp.setNext(first);
        }
    }

    public void showAll(){
        Boy temp = first;
        do {
            System.out.println(temp);
            temp = temp.getNext();
        } while (!Objects.equals(temp,first));
    }
}

class Boy{
    private int no;
    private Boy next;


    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
