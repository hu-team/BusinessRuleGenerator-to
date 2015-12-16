package com.brg.domain.rules;

import com.brg.domain.BusinessRule;
import com.brg.domain.RuleValueBundle;


public class TupleCompareRule extends BusinessRule{

    public TupleCompareRule(RuleValueBundle bundle) {
        super(bundle);
    }

    @Override
    public boolean validateRule() {
        return false;
    }
}
