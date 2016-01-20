package com.brg.domain.rules;

import com.brg.analyse.ValidateRuleHelper;
import com.brg.domain.BusinessRule;


public class InterEntityCompareRule extends BusinessRule {

    public InterEntityCompareRule() {
        super();
    }

    @Override
    public boolean validateRule() {
        ValidateRuleHelper validateRuleHelper = new ValidateRuleHelper(this.getValues());
        return validateRuleHelper.RuleType("InterEntity");
    }
}
