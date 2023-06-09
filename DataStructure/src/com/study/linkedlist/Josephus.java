package com.study.linkedlist;

import java.util.Objects;

public class Josephus {
    public static void main(String[] args) {
        AnnularSingeLinkedList annularSingeLinkedList = new AnnularSingeLinkedList(5);
        annularSingeLinkedList.showAll();

        annularSingeLinkedList.josephusOutAnnular(1,2);
    }
}

class AnnularSingeLinkedList{
    private Boy first;

    private void isFirstNull(){
        if (Objects.isNull(first)) throw new NullPointerException();
    }

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
        isFirstNull();
        Boy temp = first;
        do {
            System.out.println(temp);
            temp = temp.getNext();
        } while (!Objects.equals(temp,first));
    }

    public void josephusOutAnnular(int startNo,int numOfTime){
        Boy pre = first;
        // 得到 first 前一个节点
        while (!Objects.equals(pre.getNext(),first)) pre = pre.getNext();
        // 将first 与pre都走到 startNo位置
        for (int i = 0; i < startNo-1; i++) {
            pre = pre.getNext();
            first = first.getNext();
        }
        // 按照 从startNo开始 上面已经将first、pre走到startNo位置了  按照 numOfTime次出链表
        int ofTime = 1;
        while (true){
            // 如果 pre==first 则代表链表已经只有最后一条数据了
            if (pre==first){
                System.out.println("==> " + first);
                first = null;
                break;
            }
            // 判断是否 走了numOfTime次  如果条件成立则取出当前数据
            if (ofTime == numOfTime){
                System.out.println("==> " + first);
                first = first.getNext();
                pre.setNext(first);
                ofTime = 1;
            }
            pre = pre.getNext();
            first = first.getNext();
            ofTime++;
        }
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
