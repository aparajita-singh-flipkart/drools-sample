package com.fkhmarketplace.pricing.poc.pojo;

public class Product {
    String brand;
    String category;

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

    public Product(String brand, String category) {
        this.brand = brand;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
