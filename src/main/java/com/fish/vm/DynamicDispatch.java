package com.fish.vm;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/23
 */
public class DynamicDispatch {

    static abstract class Human {
        protected void sayHello() {
            System.out.println("Hello guy!");
        }
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("Hello gentle man!");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("Hello lady!");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }

}
