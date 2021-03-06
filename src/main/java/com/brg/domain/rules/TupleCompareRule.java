package com.brg.domain.rules;

import com.brg.ServiceProvider;
import com.brg.domain.BusinessRule;


public class TupleCompareRule extends BusinessRule{

    public TupleCompareRule() {
        super();
    }

    @Override
    public boolean validateRule() {
        return ServiceProvider.getInstance().getAnalyseService().validateRule(this.getValues(), "Tuple");
    }
}
