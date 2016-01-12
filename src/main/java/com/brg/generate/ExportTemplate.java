package com.brg.generate;

import com.brg.domain.DatabaseType;
import com.brg.domain.RuleOperand;
import com.brg.domain.RuleValueBundle;
import org.stringtemplate.v4.ST;

public interface ExportTemplate {
    String fillTemplate(RuleValueBundle bundle, RuleOperand operand);

    DatabaseType getType();

    void setCode(String c);
    void setName(String n);

    String getCode();
    String getName();
    String getSource();

    void setTemplate(ST template);
    ST getTemplate();
}
