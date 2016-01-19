package com.brg.generate;

import com.brg.domain.BusinessRule;
import com.brg.domain.DatabaseType;
import com.brg.domain.RuleOperand;
import com.brg.domain.RuleValueBundle;

public class Export {

    private String triggerIdentifier;
    private String output;

    private RuleValueBundle bundle;
    private RuleOperand operand;

    private DatabaseType databaseType;

    private ExportTemplate template;

    public Export(BusinessRule rule){
        this.triggerIdentifier = rule.getCode();
        this.bundle = rule.getValues();
        this.operand = rule.getOperand();
    }

    public ExportTemplate getTemplate() {
        return this.template;
    }

    public String getTriggerIdentifier() {
        return this.triggerIdentifier;
    }

    public String getOutput() throws Exception {
        return this.template.fillTemplate(this.bundle, this.operand, this.triggerIdentifier);
    }

    public DatabaseType getDatabaseType(){
        return this.databaseType;
    }

    public void setDatabaseType(DatabaseType dt){
        this.databaseType = dt;
    }

    public void setTriggerIdentifier(String triggerIdentifier) {
        this.triggerIdentifier = triggerIdentifier;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void setTemplate(ExportTemplate template) {
        this.template = template;
    }
}
