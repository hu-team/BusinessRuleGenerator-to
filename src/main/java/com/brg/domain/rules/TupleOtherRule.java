package com.brg.domain.rules;

import com.brg.ServiceProvider;
import com.brg.domain.BusinessRule;


public class TupleOtherRule extends BusinessRule {

    public TupleOtherRule() {
        super();
    }

    @Override
    public boolean validateRule() {
        return ServiceProvider.getInstance().getAnalyseService().validateRule(this.getValues(), "Tuple");
    }
}
