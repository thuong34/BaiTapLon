package com.example.thuoc.model;

import java.util.ArrayList;
import java.util.List;

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
        boolean isProductExist = false;
        for (product item : listProduct) {
            if (item.getId() == p.getId()) {
                int newQuantity = item.getAmount() + 1;
                item.setAmount(newQuantity);
                isProductExist = true;
                break;
            }
        }
        if (!isProductExist) {
            listProduct.add(p);
        }
        totalprice+=p.getPrice();
    }
    public void RemoveProduct(product p){
        listProduct.remove(p);
        totalprice -=  p.getPrice();
    }

    public float getTotalprice() {

        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }




}
