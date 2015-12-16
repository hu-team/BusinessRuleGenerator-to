package com.brg.generate;

import com.brg.domain.BusinessRule;
import com.brg.domain.DatabaseType;

import java.util.ArrayList;


public class Export {

    private String triggerIdentifier;
    private String output;

    private DatabaseType databaseType;

    private ArrayList<ExportTemplate> templates;

    public Export(BusinessRule rule){
        templates = new ArrayList<ExportTemplate>();
    }

    public ArrayList<ExportTemplate> getTemplates() {
        return this.templates;
    }

    public String getTriggerIdentifier() {
        return this.triggerIdentifier;
    }

    public String getOutput() {
        return this.output;
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

    public void setTemplates(ExportTemplate template) {
        this.templates.add(template);
    }
}
