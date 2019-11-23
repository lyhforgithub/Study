package com.lyh.gmail.user.demo;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo5 {
    private static int val = 0;
    private static AtomicInteger valA = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <100 ; j++) {
                new Thread(() ->
                {
                        getNext();
                        try {
                            /*为了突出线程效果*/
                           //Thread.sleep(300);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                }).start();
            }
        }
        System.out.println(val);
        System.out.println("原子"+valA);
    }

    public static  void getNext() {
        val++;
        valA.incrementAndGet();
        //System.out.println(val);
    }
}
