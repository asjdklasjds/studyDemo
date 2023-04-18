package com.study.hashtable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * 哈希表 （散列） 实现
 * @author yan
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(4);
        Scanner scanner = new Scanner(System.in);
        String key = "";
        while (true){
            System.out.println("put:添加\tget:获取\tprint:打印所有\texit:退出");
            key = scanner.next();
            switch (key){
                case "put": {
                    System.out.println("请输入UserId");
                    int userId = scanner.nextInt();
                    System.out.println("请输入UserName");
                    String userName = scanner.next();
                    User us = new User(userId, userName);
                    hashTab.put(us);
                    break;
                }
                case "putO": {
                    System.out.println("请输入UserId");
                    int userId = scanner.nextInt();
                    System.out.println("请输入UserName");
                    String userName = scanner.next();
                    User us = new User(userId, userName);
                    hashTab.putByOrder(us);
                    break;
                }
                case "get": {
                    System.out.println("请输入UserId");
                    int userId = scanner.nextInt();
                    System.out.println(hashTab.get(userId));
                    break;
                }case "getA": {
                    System.out.println("请输入UserId");
                    int userId = scanner.nextInt();
                    System.out.println(hashTab.getAllByUserId(userId));
                    break;
                }
                case "delete": {
                    System.out.println("请输入UserId");
                    int userId = scanner.nextInt();
                    hashTab.delete(userId);
                    break;
                }
                case "print":{
                    hashTab.printList();
                    break;
                }
                case "exit":{
                    scanner.close();
                    System.exit(0);
                }
            }
        }
    }
}

class HashTab{
    private HashTabArray[] hashTabArrays;

    private int size;

    public HashTab(int size){
        this.size = size;
        hashTabArrays = new HashTabArray[size];
        // 注意一定要初始化HashTabArray 不然默认为null
        for (int i = 0; i < size; i++) {
            hashTabArrays[i] = new HashTabArray();
        }
    }

    public void put(User user){
        hashTabArrays[hashFun(user.getUserId())].put(user);
    }

    public void putByOrder(User user){
        hashTabArrays[hashFun(user.getUserId())].putByOrder(user);
    }

    public User get(int userId){
        return hashTabArrays[hashFun(userId)].get(userId);
    }
    
    public void printList(){
        for (int i = 0; i < size; i++) {
            hashTabArrays[i].printList(i);
        }
    }

    public List<User> getAllByUserId(int userId){
        return hashTabArrays[hashFun(userId)].getAllByUserId(userId);
    }

    public void delete(int userId){
        hashTabArrays[hashFun(userId)].delete(userId);
    }

    public int hashFun(int userId){
        return userId % size;
    }
}

class HashTabArray {
    private User head;


    public void put(User user){
        if (Objects.isNull(head)) {
            head = user;
            return;
        }
        User temp = head;
        while (!Objects.isNull(temp.getNext())) temp = temp.getNext();
        temp.setNext(user);
    }

    public void putByOrder(User user){
        if (Objects.isNull(head)) {
            head = user;
            return;
        }
        if (head.getUserId() > user.getUserId()){
            user.setNext(head);
            head = user;
            return;
        }
        User temp = head.getNext();
        User curTmep = head;
        while (!Objects.isNull(temp)){
            if (temp.getUserId() > user.getUserId()){
                user.setNext(curTmep.getNext());
                curTmep.setNext(user);
                return;
            } else
                curTmep = curTmep.getNext();
            temp = temp.getNext();
        }
        curTmep.setNext(user);
    }

    public User get(int userId){
        if (Objects.isNull(head)) throw new RuntimeException("head is null");
        User temp = head;
        while (!Objects.isNull(temp)){
            if (temp.getUserId() == userId){
                return temp;
            }
            temp= temp.getNext();
        }
        return null;
    }

    public List<User> getAllByUserId(int userId){
        if (Objects.isNull(head)) throw new RuntimeException("head is null");
        User temp = head;
        List<User> list = new ArrayList<>();
        while (!Objects.isNull(temp)){
            if (temp.getUserId() == userId){
                list.add(temp);
            }
            temp= temp.getNext();
        }
        return list;
    }

    public void delete(int userId){
        if (Objects.isNull(head)) throw new RuntimeException("head is null");
        if (head.getUserId() == userId) {
            System.out.println("删除成功！");
            head = head.getNext();
            return;
        }
        User temp = head.getNext();
        User curTemp = head;
        while (!Objects.isNull(temp)){
            if (temp.getUserId() == userId) {
                curTemp.setNext(temp.getNext());
                System.out.println("删除成功！");
                return;
            }
            else
                curTemp = curTemp.getNext();
            temp = temp.getNext();
        }
        System.out.println("未找到需要删除的元素！！");
    }

    public void printList(int index){
        if (Objects.isNull(head)) {
            System.out.println(""+(index+1)+" head is null ");
            return;
        }
        User temp = head;
        System.out.print(""+(index+1)+" => ");
        while (!Objects.isNull(temp)){
            System.out.printf("user {userId = %d,userName = %s} \t",temp.getUserId(),temp.getUserName());
            temp= temp.getNext();
        }
        System.out.println();
    }
}

/**
 * 用户表
 */
class User{
    private int userId;
    private String userName;
    private User next;

    public User(int userId,String userName){
        this.userId = userId;
        this.userName = userName;
    }

    public User getNext() {
        return next;
    }

    public void setNext(User next) {
        this.next = next;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
