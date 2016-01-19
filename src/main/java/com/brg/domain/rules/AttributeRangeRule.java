package com.brg.domain.rules;

import com.brg.analyse.ValidateRuleHelper;
import com.brg.domain.BusinessRule;


public class AttributeRangeRule extends BusinessRule {

    public AttributeRangeRule() {
        super();
    }

    @Override
    public boolean validateRule() {
        ValidateRuleHelper validateRuleHelper = new ValidateRuleHelper(this.getValues());
        return validateRuleHelper.RuleType("Attribute");
    }
}
