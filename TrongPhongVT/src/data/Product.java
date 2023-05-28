/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author trien
 */
public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String status;

    public Product(String id, String name, double price, int quantity, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + quantity + "," + status;
    }
    
    public void showInfor(){
        System.out.printf("|%-10s|%-20s|%-10.2f|%-10d|%-15s|\n",id,name,price,quantity,status);
    }
}
