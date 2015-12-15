package com.brg.domain.rules;

import com.brg.domain.BusinessRule;
import com.brg.domain.RuleValueBundle;

/**
 * Developed by Arjan.
 */
public class InterEntityCompareRule extends BusinessRule {

    public InterEntityCompareRule(RuleValueBundle bundle) {
        super(bundle);
    }

    @Override
    public boolean validateRule() {
        return false;
    }
}
