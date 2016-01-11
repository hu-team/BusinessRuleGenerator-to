package com.brg.generate;

import com.brg.domain.DatabaseType;
import com.brg.domain.RuleOperand;
import com.brg.domain.RuleValueBundle;
import org.stringtemplate.v4.ST;

import java.util.Iterator;
import java.util.Map;


public class OracleExport implements ExportTemplate {

    private ST template;

    @Override
    public String fillTemplate(RuleValueBundle bundle, RuleOperand operand) {
        for(String key: bundle.getKeys()) {
            String templateKey = key.replaceAll("\\.", "_");

            this.template.add(templateKey, bundle.getValue(key));
        }

        // Add operator
        if (operand != null) {
            this.template.add("operand", operand.getSign());
        }

        return this.template.render();
    }

    @Override
    public DatabaseType getType() {
        return DatabaseType.ORACLE;
    }

    @Override
    public String getCode() {
        return null;
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getSource() {
        return null;
    }

    @Override
    public void setTemplate(ST template) {
        this.template = template;
    }

    @Override
    public ST getTemplate() {
        return this.template;
    }
}
