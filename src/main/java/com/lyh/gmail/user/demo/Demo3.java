package com.lyh.gmail.user.demo;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * cas(userAtomicReference.compareAndSet),比较的对象在内存中的地址
 * 值的改变，不影响比较的结果
 */
public class Demo3 {
    void atomicVersion1(){
        User z3 = new User("tom",18);
        User l4 = new User("alice",16);
        User w5 = new User();
        w5 = l4;
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(z3);
        z3.setAge(20);
        System.out.println(userAtomicReference.compareAndSet(z3,l4)+"\t"+z3);
        System.out.println(userAtomicReference.compareAndSet(w5,z3)+"\t"+l4);
    }

    /**
     *
     * Integer里弄了一个缓存，对于在 -128—127 之间的数值，会直接使用该缓存里的对象
     * 超过范围，地址则会变化
     */
    void atomicVersion2(){
        AtomicStampedReference<Integer> integerAtomicStampedReference = new AtomicStampedReference<Integer>(127,1);
        new Thread(() ->{
            boolean result = integerAtomicStampedReference.compareAndSet(127, 66, 1, 2);
            if(result){
                System.out.println(result+"\t"+integerAtomicStampedReference.getStamp()
                        +"\t"
                        +integerAtomicStampedReference.getReference()
                        +"\t"
                        +Thread.currentThread().getName());
            }else {
                System.out.println("更改失败"+Thread.currentThread().getName());
            }

        },"tom").start();

        new Thread(() ->{
            Integer reference = integerAtomicStampedReference.getReference();
            int stamp = integerAtomicStampedReference.getStamp();
            boolean result = integerAtomicStampedReference.compareAndSet(66, 4396, stamp, 3);
            if(result){
                System.out.println(result+"\t"+integerAtomicStampedReference.getStamp()
                        +"\t"
                        +integerAtomicStampedReference.getReference()
                        +"\t"
                        +Thread.currentThread().getName());
            }else {
                System.out.println("更改失败"+Thread.currentThread().getName());
            }
        },"ben").start();
    }

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            Demo3 demo3 = new Demo3();
            demo3.atomicVersion2();
            System.out.println("___________________"+i+"___________________");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

    }
}


class User{
    String userName;
    int age;

    User(){}

    public User(String userName,int age){
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}