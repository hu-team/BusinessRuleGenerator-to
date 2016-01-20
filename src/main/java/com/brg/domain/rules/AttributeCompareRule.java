package com.brg.domain.rules;

import com.brg.ServiceProvider;
import com.brg.domain.BusinessRule;


public class AttributeCompareRule extends BusinessRule{

    public AttributeCompareRule() {
        super();
    }

    @Override
    public boolean validateRule() {
        return ServiceProvider.getInstance().getAnalyseService().validateRule(this.getValues(), "Attribute");
    }
}
