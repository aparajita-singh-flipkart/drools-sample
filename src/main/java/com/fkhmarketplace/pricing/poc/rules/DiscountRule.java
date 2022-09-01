package com.fkhmarketplace.pricing.poc.rules;

import com.fkhmarketplace.pricing.poc.operator.Percent;
import com.fkhmarketplace.pricing.poc.pojo.DiscountBasePrice;
import com.fkhmarketplace.pricing.poc.pojo.DiscountInputSignals;

public class DiscountRule implements Rule<DiscountInputSignals, DiscountBasePrice> {
    public DiscountRule(double percent) {
        this.percent = new Percent(percent);
    }

    Percent percent;

    @Override
    public DiscountBasePrice evaluate(DiscountInputSignals inputSignals, DiscountBasePrice ruleOutput) {
        double discountValue = percent.getPercent(inputSignals.getLot().getMrp());
        double sellingPrice = inputSignals.getLot().getMrp() - discountValue;
        ruleOutput.setSellingPrice(sellingPrice);
        System.out.println("evaluating => " + inputSignals.toString() + " ; " + ruleOutput.toString());
        return ruleOutput;
    }
}
