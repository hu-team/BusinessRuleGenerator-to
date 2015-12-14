package com.brg.model.rules;

import com.brg.model.BusinessRule;
import com.brg.model.RuleValueBundle;

/**
 * Developed by Arjan.
 */
public class ModifyRule extends BusinessRule {

    public ModifyRule(RuleValueBundle bundle) {
        super(bundle);
    }

    @Override
    public boolean validateRule() {
        return false;
    }
}
