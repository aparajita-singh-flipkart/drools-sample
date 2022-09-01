package com.fkhmarketplace.pricing.poc.pojo;

public class DiscountBasePrice implements RuleOutput {
    Lot lot;
    double sellingPrice;

    public DiscountBasePrice() {

    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public DiscountBasePrice(Lot lot) {
        this.lot = lot;
    }

    @Override
    public String toString() {
        return "DiscountBasePrice{" +
                "lot=" + lot +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
