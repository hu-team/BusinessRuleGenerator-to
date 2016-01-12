package com.brg.generate;

import com.brg.domain.DatabaseType;
import com.brg.domain.RuleOperand;
import com.brg.domain.RuleValueBundle;
import org.stringtemplate.v4.ST;


public class MySQLExport extends BaseTemplate implements ExportTemplate {

    private ST template;
    private String code;

    @Override
    public String fillTemplate(RuleValueBundle bundle, RuleOperand operand) {
        return this.fillTemplateWithBundle(this.template, bundle, operand);
    }

    @Override
    public DatabaseType getType() {
        return DatabaseType.MYSQL;
    }

    @Override
    public void setCode(String code){
        this.code = code;
    }


    @Override
    public String getCode() {
        return this.code;
    }
    //MySqlcode

    @Override
    public String getSource() {
        return template.render();
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
