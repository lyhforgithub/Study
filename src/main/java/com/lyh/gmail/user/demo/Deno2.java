package com.lyh.gmail.user.demo;


import java.util.concurrent.atomic.AtomicInteger;

public class Deno2 {

    public static void main(String[] args) {
        Deno2 deno2 = new Deno2();
        AtomicInteger atomicInteger = deno2.testAtomicInteger();
        System.out.println("返回的结果"+atomicInteger);

    }


    /**
     * 验证volatile的可见性，可以解决可见性问题
     */
     void testVolatile() {
        Mydata mydata = new Mydata();
        new Thread(){
            @Override
            public void run() {
                Mydata mydata = new Mydata();
                try {
                    System.out.println("开启一个线程");
                    sleep(2000);
                    int i = mydata.updateTo();
                    System.out.println("已修改共享内存" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        System.out.println(mydata.a);
        while (mydata.a == 0) {

        }
        System.out.println("主线程收到内存修改通知" + mydata.a);
    }

    private static int b = 0;
    private static  int c = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    static synchronized void getNext(){
        c++;
        atomicInteger.getAndIncrement();
    }
    /**
     * AtomicInteger属于原子性操作
     */
     AtomicInteger testAtomicInteger(){
        try {
            for (int i = 0; i <100 ; i++) {
                new Thread(() -> {
                        for (int j = 0; j < 100; j++) {

                                 getNext();

                        }
                }).start();
//                System.out.println("循环中的原子" + atomicInteger + Thread.currentThread().getName());
                }
//            while (Thread.activeCount()>2){
//                Thread.yield();
//            }
            //System.out.println("原子"+atomicInteger+Thread.currentThread().getName());
            System.out.println("普通"+c);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return atomicInteger;
        }

    }

}

 class Mydata {
    //加上static，保证只创建一个a属性
    //若不加，则会在每次初始化Mydata对象时，重新创建一个新的a
    public static volatile int a = 0;

    public int updateTo() {
        return this.a = 60;
    }
}

class number{
    private int e;

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }
}
