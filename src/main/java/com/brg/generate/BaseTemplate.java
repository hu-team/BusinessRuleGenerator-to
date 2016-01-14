package com.brg.generate;

import com.brg.domain.RuleOperand;
import com.brg.domain.RuleValueBundle;
import org.stringtemplate.v4.ST;

import java.util.ArrayList;

public abstract class BaseTemplate {
    protected String code;
    protected String ruleClass;
    protected String source;

    @SuppressWarnings("unchecked")
    protected String fillTemplateWithBundle(ST template, RuleValueBundle bundle, RuleOperand operand, String code) {
        template = new ST(template);

        for(String key: bundle.getKeys()) {
            String templateKey = key.replaceAll("\\.", "_");
            String templateValue = null;

            if ("list.list".equals(key)) {
                // We need to parse it first!
                ArrayList<String> stringList = null;

                if (bundle.getValue(key) instanceof String) {
                    stringList = new ArrayList<String>();
                    stringList.add((String) bundle.getValue(key));
                }

                if (! (bundle.getValue(key) instanceof ArrayList)) {
                    continue; // Skip invalid value
                }

                // Parse the array to the string
                ArrayList<String> oldStringList = (ArrayList<String>) bundle.getValue(key);
                stringList = new ArrayList<String>();
                // Add quotes
                for (String item: oldStringList) {
                    stringList.add("'" + item + "'");
                }

                // Join parts
                templateValue = String.join(", ", stringList);
            } else {
                if (bundle.getValue(key) instanceof String) {
                    templateValue = (String)bundle.getValue(key);
                } else {
                    System.err.println("Bundle entry should be a string, maybe update the application will help?");
                }
            }

            // Add mapping to the templatestring
            template.add(templateKey, templateValue);
        }

        // Add operator
        if (operand != null) {
            template.add("operand", operand.getSign());
        }

        // Add code
        if (code != null) {
            template.add("code", code);
        }

        return template.render();
    }

    protected ST getTemplate() {
        return new ST(this.source, '{', '}');
    }
}
