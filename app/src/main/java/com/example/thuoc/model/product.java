package com.example.thuoc.model;

public class product {
    private int id;
    private String name;
    private float price;

    private int res;

    public product(int id, String name, float price, int res) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.res = res;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getRes() {
        return res;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
