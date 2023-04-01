package com.study.linkedlist;

import java.util.Objects;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        HeroNode heroNode1 = new HeroNode(1,"张三","zhangsan");
        HeroNode heroNode2 = new HeroNode(2,"李四","lisi");
        HeroNode heroNode3 = new HeroNode(3,"王五","wangwu");
        HeroNode heroNode4 = new HeroNode(4,"甲","jia");
        doubleLinkedList.addByOrder(heroNode3);
        doubleLinkedList.addByOrder(heroNode4);
        doubleLinkedList.addByOrder(heroNode4);
        doubleLinkedList.addByOrder(heroNode1);
        doubleLinkedList.addByOrder(heroNode2);

        doubleLinkedList.list();

        System.out.println("修改后的链表~~");
        doubleLinkedList.update(new HeroNode(4,"乙","yi"));

        doubleLinkedList.list();

        System.out.println("删除后的链表~~");
        doubleLinkedList.delete(2);
        doubleLinkedList.list();
    }
}


class DoubleLinkedList{
    private HeroNode head = new HeroNode();

    public HeroNode getHead() {
        return head;
    }

    public void add(HeroNode node){
        HeroNode temp = head;
        while (!Objects.isNull(temp.next))
            temp = temp.next;
        temp.next = node;
        node.pre = temp;
    }

    public void addByOrder(HeroNode node){
        HeroNode temp = head;
        boolean flag = false;
        while (true){
            if (Objects.isNull(temp.next))
                break;
            if (temp.next.no > node.no)
                break;
            else if (temp.next.no == node.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag){
            node.pre = temp;
            node.next = temp.next;
            temp.next = node;
        }else {
            System.out.println("当前节点已存在 不能再添加！" + node);
        }
    }

    public void update(HeroNode updNode){
        HeroNode temp = head;
        while (!Objects.isNull(temp.next)){
            if (updNode.no == temp.next.no){
                temp.next.name = updNode.name;
                temp.next.nickname = updNode.nickname;
                break;
            }
            temp = temp.next;
        }
    }

    public void delete(int no){
        HeroNode temp = head;
        while (!Objects.isNull(temp.next)){
            if (temp.next.no == no){
                temp.next = temp.next.next;
                temp.pre = temp;
                break;
            }
            temp = temp.next;
        }
    }

    public void list(){
        HeroNode temp = head;
        while (!Objects.isNull(temp.next)){
            System.out.println(temp.next);
            temp = temp.next;
        }
    }

}

class HeroNode{

    public HeroNode pre;

    public HeroNode next;

    public int no;

    public String name;

    public String nickname;


    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    public HeroNode(){}

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
