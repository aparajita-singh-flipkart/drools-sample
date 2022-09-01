package com.fkhmarketplace.pricing.poc.pojo;

public class DiscountInputSignals implements InputSignals{
    Product product;
    Seller seller;
    Lot lot;

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "DiscountInputSignals{" +
                "product=" + product +
                ", seller=" + seller +
                ", lot=" + lot +
                '}';
    }

    public DiscountInputSignals(Product product, Seller seller, Lot lot) {
        this.product = product;
        this.seller = seller;
        this.lot = lot;
    }
}
