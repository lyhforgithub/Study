package com.lyh.gmail.user.demo;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * arrayList线程安全的方法
 * 三种方法
 * 前两种加锁
 * 第三种为复制后扩容
 */
public class Demo4 {
    public static void main(String[] args) {
        //List<String> stringArrayList = new Vector<>();
        //List<String> stringArrayList = Collections.synchronizedList(new ArrayList<>());
        //List<String> stringArrayList = new CopyOnWriteArrayList<>();
        //以上三种为线程安全的arryList
        List<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            new Thread(() ->{
                stringArrayList.add(Thread.currentThread().getName());
            },String.valueOf(i)).start();
        }
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(stringArrayList.size());
    }


}

