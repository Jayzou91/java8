package com.fish.parallel;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/7/22
 */
public class Product {
    private Integer id;
    private String name;

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
