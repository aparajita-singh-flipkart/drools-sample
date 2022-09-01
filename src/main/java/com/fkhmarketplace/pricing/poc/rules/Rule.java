package com.fkhmarketplace.pricing.poc.rules;

import com.fkhmarketplace.pricing.poc.pojo.InputSignals;
import com.fkhmarketplace.pricing.poc.pojo.RuleOutput;

public interface Rule<T, U> {
    public U evaluate(T inputSignals, U ruleOutput);
}
