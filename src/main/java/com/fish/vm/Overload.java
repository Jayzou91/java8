package com.fish.vm;

import java.io.Serializable;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/23
 */
public class Overload {

    public static void sayHello(Object arg) {
        System.out.println("Hello object");
    }

    public static void sayHello(int arg) {
        System.out.println("Hello int");
    }

    public static void sayHello(long arg) {
        System.out.println("Hello long");
    }

    public static void sayHello(Character arg) {
        System.out.println("Hello character");
    }

    public static void sayHello(char arg) {
        System.out.println("Hello char");
    }

    public static void sayHello(char... arg) {
        System.out.println("Hello char...");
    }

    public static void sayHello(Serializable arg) {
        System.out.println("Hello Serializable");
    }

    public static void main(String[] args) {
        sayHello('a');
    }
}
