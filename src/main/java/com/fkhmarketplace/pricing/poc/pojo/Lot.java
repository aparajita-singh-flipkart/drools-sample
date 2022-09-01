package com.fkhmarketplace.pricing.poc.pojo;

public class Lot {
    double mrp;

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "mrp=" + mrp +
                '}';
    }

    public Lot(double mrp) {
        this.mrp = mrp;
    }
}
