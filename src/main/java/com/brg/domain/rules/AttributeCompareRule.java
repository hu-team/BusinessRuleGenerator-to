package com.brg.domain.rules;

import com.brg.domain.BusinessRule;
import com.brg.domain.RuleValueBundle;

/**
 * Developed by Arjan.
 */
public class AttributeCompareRule extends BusinessRule{

    public AttributeCompareRule(RuleValueBundle bundle) {
        super(bundle);
    }

    @Override
    public boolean validateRule() {
        return false;
    }
}
