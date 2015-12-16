package com.brg.persistence;

import com.brg.domain.RuleValueBundle;

import java.util.ArrayList;

public class RuleValueBundleService {

    private ArrayList<RuleValueBundle> values = new ArrayList<RuleValueBundle>();

    public ArrayList<RuleValueBundle> getRuleValueBundle() {
        return this.values;
    }

}
