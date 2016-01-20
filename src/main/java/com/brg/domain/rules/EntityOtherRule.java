package com.brg.domain.rules;

import com.brg.ServiceProvider;
import com.brg.domain.BusinessRule;


public class EntityOtherRule extends BusinessRule {

    public EntityOtherRule() {
        super();
    }

    @Override
    public boolean validateRule() {
        return ServiceProvider.getInstance().getAnalyseService().validateRule(this.getValues(), "Entity");
    }
}
