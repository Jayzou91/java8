package com.fish.reflect;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/23
 */
public class Book {

    private String bookName;
    private int length;
    private int width;

    public Book(String bookName, int length, int width) {
        this.bookName = bookName;
        this.length = length;
        this.width = width;
    }

    private int pageArea() {
        return length * width;
    }

    private String getBookName() {
        return bookName;
    }

    public void showBookDetail() {
        System.out.println(pageArea());
        System.out.println(getBookName());
    }
}
