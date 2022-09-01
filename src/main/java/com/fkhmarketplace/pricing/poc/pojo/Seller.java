package com.fkhmarketplace.pricing.poc.pojo;

public class Seller {
    String seller_apob_id;

    @Override
    public String toString() {
        return "Seller{" +
                "seller_apob_id='" + seller_apob_id + '\'' +
                '}';
    }

    public String getSeller_apob_id() {
        return seller_apob_id;
    }

    public void setSeller_apob_id(String seller_apob_id) {
        this.seller_apob_id = seller_apob_id;
    }

    public Seller(String seller_apob_id) {
        this.seller_apob_id = seller_apob_id;
    }
}
