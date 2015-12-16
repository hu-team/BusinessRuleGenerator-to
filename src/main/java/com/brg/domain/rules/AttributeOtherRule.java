package com.brg.domain.rules;

import com.brg.domain.BusinessRule;
import com.brg.domain.RuleValueBundle;


public class AttributeOtherRule extends BusinessRule {

    public AttributeOtherRule(RuleValueBundle bundle) {
        super(bundle);
    }

    @Override
    public boolean validateRule() {
        return false;
    }
}
