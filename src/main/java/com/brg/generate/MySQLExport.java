package com.brg.generate;

import com.brg.domain.DatabaseType;
import com.brg.domain.RuleOperand;
import com.brg.domain.RuleValueBundle;
import org.stringtemplate.v4.ST;


public class MySQLExport implements ExportTemplate {

    private ST template;


    @Override
    public String fillTemplate(RuleValueBundle bundle, RuleOperand operand) {
        return null;
    }

    @Override
    public DatabaseType getType() {
        return null;
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
