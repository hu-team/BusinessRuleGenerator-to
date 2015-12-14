package com.brg.model.rules;

import com.brg.model.BusinessRule;
import com.brg.model.RuleValueBundle;

/**
 * Developed by Arjan.
 */
public class AttributeListRule extends BusinessRule {

    public AttributeListRule(RuleValueBundle bundle) {
        super(bundle);
    }

    @Override
    public boolean validateRule() {
        return false;
    }
}
