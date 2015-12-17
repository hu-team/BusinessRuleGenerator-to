package com.brg.generate;

import com.brg.domain.DatabaseType;
import com.brg.domain.RuleValueBundle;
import org.stringtemplate.v4.ST;

public interface ExportTemplate {
    String fillTemplate(RuleValueBundle bundle);

    DatabaseType getType();

    String getCode();
    String getName();
    String getSource();

    void setTemplate(ST template);
    ST getTemplate();
}
