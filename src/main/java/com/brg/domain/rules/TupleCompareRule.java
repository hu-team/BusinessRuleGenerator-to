package com.brg.domain.rules;

import com.brg.domain.BusinessRule;


public class TupleCompareRule extends BusinessRule{

    public TupleCompareRule() {
        super();
    }

    @Override
    public boolean validateRule() {
        System.out.println(this.getValues().getKeys());
        return false;
    }
}
