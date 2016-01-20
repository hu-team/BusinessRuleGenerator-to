package com.brg.domain.rules;

import com.brg.ServiceProvider;
import com.brg.analyse.ValidateRuleHelper;
import com.brg.domain.BusinessRule;


public class InterEntityCompareRule extends BusinessRule {

    public InterEntityCompareRule() {
        super();
    }

    @Override
    public boolean validateRule() {
        return ServiceProvider.getInstance().getAnalyseService().validateRule(this.getValues(), "InterEntity");
    }
}
