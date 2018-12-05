package com.fish.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/23
 */
public class PrivateFieldDemo {

    //Access all private fields of the class.
    public void printAllPrivateFields(Book book) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = book.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
                System.out.println(field.getName()+" : "+field.get(book));
            }
        }
    }
    //Access private field by using filed name.
    public void printFieldValue(Book book, String fieldName) throws NoSuchFieldException,
            SecurityException, IllegalArgumentException, IllegalAccessException {

        Field field = book.getClass().getDeclaredField(fieldName);
        if (Modifier.isPrivate(field.getModifiers())) {
            field.setAccessible(true);
            System.out.println(fieldName + " : "+field.get(book));
        }
    }
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException,
            NoSuchFieldException, SecurityException {

        Book book = new Book("Core Java", 12, 5);
        PrivateFieldDemo ob = new PrivateFieldDemo();
        //print all private fields and their value
        ob.printAllPrivateFields(book);
        System.out.println("-----------------------");
        //print private field value by field name
        ob.printFieldValue(book, "bookName");
    }
}
