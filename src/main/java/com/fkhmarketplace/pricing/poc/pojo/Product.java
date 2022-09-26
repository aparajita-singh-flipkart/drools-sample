package com.fkhmarketplace.pricing.poc.pojo;

public class Product {
    String brand;
    String category;
    int id;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product(String brand, String category) {
        this.brand = brand;
        this.category = category;
    }

    public Product(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
