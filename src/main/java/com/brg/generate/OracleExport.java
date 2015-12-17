package com.brg.generate;

import com.brg.domain.DatabaseType;
import com.brg.domain.RuleValueBundle;
import org.stringtemplate.v4.ST;


public class OracleExport implements ExportTemplate {

    private ST template;

    private RuleValueBundle valueBundle;

    public OracleExport(RuleValueBundle values) {
        this.valueBundle = values;
    }

    @Override
    public String fillTemplate(RuleValueBundle bundle) {
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
