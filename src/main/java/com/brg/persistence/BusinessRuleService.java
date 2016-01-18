package com.brg.persistence;


import com.brg.ServiceProvider;
import com.brg.domain.BusinessRule;

import java.util.ArrayList;


public class BusinessRuleService {

    private ArrayList<BusinessRule> rules;

    /**
     * Will reload all rules from the database into the local application memory.
     */
    public void reloadRules() {
        this.rules = ServiceProvider.getInstance().getDaoService().getAllBusinessRules();
    }

    /**
     * Get rules
     * @return rules
     */
    public ArrayList<BusinessRule> getRules() {
        return this.rules;
    }
}
