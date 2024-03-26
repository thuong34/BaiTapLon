package com.example.thuoc.model;

import java.util.ArrayList;

public class giohang {
    ArrayList<product> listProduct=new ArrayList<>();

    float totalprice=0;
    public giohang(){

    }

    public giohang(ArrayList<product> listProduct) {
        this.listProduct = listProduct;
    }

    public ArrayList<product> getListProduct() {
        return listProduct;
    }

    public void AddProduct(product p) {
        this.listProduct.add(p);
        totalprice+=p.getPrice();
    }
    public void RemoveProduct(product p){
        this.listProduct.remove(p);
        totalprice-=p.getPrice();
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }
}
