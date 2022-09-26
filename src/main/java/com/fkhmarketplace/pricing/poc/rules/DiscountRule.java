package com.fkhmarketplace.pricing.poc.rules;

import com.fkhmarketplace.pricing.poc.operator.Percent;
import com.fkhmarketplace.pricing.poc.pojo.DiscountBasePrice;
import com.fkhmarketplace.pricing.poc.pojo.DiscountInputSignals;
import com.fkhmarketplace.pricing.poc.pojo.Lot;
import com.fkhmarketplace.pricing.poc.pojo.Product;
import com.fkhmarketplace.pricing.poc.pojo.Seller;

public class DiscountRule {
    public DiscountRule(double percent) {
        this.percent = new Percent(percent);
    }

    public Percent getPercent() {
        return percent;
    }

    public void setPercent(Percent percent) {
        this.percent = percent;
    }

    Percent percent;

    public DiscountBasePrice evaluate(Product product, Seller seller, Lot lot, DiscountBasePrice ruleOutput) {
        double discountValue = percent.getPercent(lot.getMrp());
        double sellingPrice = lot.getMrp() - discountValue;
        ruleOutput.setSellingPrice(sellingPrice);
        System.out.println("evaluating => " + product + "," + seller + ", " + lot + " ; " + ruleOutput.toString());
        return ruleOutput;
    }

    @Override
    public String toString() {
        return "DiscountRule{" +
                "percent=" + percent +
                '}';
    }
}
