package com.fish.vm;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/23
 */
public class LocalVariableSlotMain {
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }
}
