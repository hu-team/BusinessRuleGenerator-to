package com.brg.generate;

import com.brg.domain.DatabaseType;
import com.brg.domain.RuleOperand;
import com.brg.domain.RuleValueBundle;
import org.stringtemplate.v4.ST;


public class OracleExport implements ExportTemplate {

    private ST template;

    @Override
    public String fillTemplate(RuleValueBundle bundle, RuleOperand operand) {
        // TODO: Make dynamic
        this.template.add("attribute_table", bundle.getValue("attribute.table"));
        this.template.add("attribute_column", bundle.getValue("attribute.column"));
        this.template.add("compare_with", bundle.getValue("compare.with"));
        this.template.add("operand", operand.getSign());

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
