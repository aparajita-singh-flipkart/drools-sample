package com.fkhmarketplace.pricing.poc.operator;

public class Percent {
    double percent;

    public Percent(double percent) {
        this.percent = percent;
    }

    public double getPercent(double input) {
        return input * this.percent / 100;
    }
}
