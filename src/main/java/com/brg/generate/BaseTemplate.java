package com.brg.generate;

import com.brg.domain.RuleOperand;
import com.brg.domain.RuleValueBundle;
import org.stringtemplate.v4.ST;

public abstract class BaseTemplate {

    protected ST template;
    protected String code;

    protected String fillTemplateWithBundle(ST template, RuleValueBundle bundle, RuleOperand operand) {
        for(String key: bundle.getKeys()) {
            String templateKey = key.replaceAll("\\.", "_");

            template.add(templateKey, bundle.getValue(key));
        }

        // Add operator
        if (operand != null) {
            template.add("operand", operand.getSign());
        }

        return template.render();
    }
}
