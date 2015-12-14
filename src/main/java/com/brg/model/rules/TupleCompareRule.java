package com.brg.model.rules;

import com.brg.model.BusinessRule;
import com.brg.model.RuleValueBundle;

/**
 * Developed by Arjan.
 */
public class TupleCompareRule extends BusinessRule{

    public TupleCompareRule(RuleValueBundle bundle) {
        super(bundle);
    }

    @Override
    public boolean validateRule() {
        return false;
    }
}
