package com.fkhmarketplace.pricing.poc.operator;

public class Percent {
    double percent;

    @Override
    public String toString() {
        return "Percent{" +
                "percent=" + percent +
                '}';
    }

    public Percent(double percent) {
        this.percent = percent;
    }

    public double getPercent(double input) {
        return input * this.percent / 100;
    }
}
