package com.brg.generate;

import com.brg.domain.DatabaseType;
import com.brg.domain.RuleValueBundle;

public interface ExportTemplate {

    public String fillTemplate(RuleValueBundle bundle);

    public DatabaseType getType();

    public String getCode();
    public String getName();
    public String getSource();

}
