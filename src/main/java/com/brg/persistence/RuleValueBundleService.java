package com.brg.persistence;

import com.brg.ServiceProvider;
import com.brg.domain.RuleValueBundle;

import java.util.ArrayList;

public class RuleValueBundleService {

    private ArrayList<RuleValueBundle> values = new ArrayList<RuleValueBundle>();

    public ArrayList<RuleValueBundle> getRuleValueBundle() {
        return this.values;
    }

    public RuleValueBundle getRuleById(int ruleID) {
        return ServiceProvider.getInstance().getDaoService().getRuleValueBundleForRule(ruleID);

    }
}
