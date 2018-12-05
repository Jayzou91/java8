package com.fish.vm;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/23
 */
public class StaticDispatch {

    static abstract class Human {}

    static class Man extends Human {

    }

    static class Woman extends Human {
    }

    public void sayHello(Human human) {
        System.out.println("Hello, guy!");
    }

    public void sayHello(Man man) {
        System.out.println("Hello, gentle man!");
    }

    public void sayHello(Woman women) {
        System.out.println("Hello, lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

        StaticDispatch sd = new StaticDispatch();
        sd.sayHello(man);
        sd.sayHello(woman);
    }
}
