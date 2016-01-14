package com.brg.generate;

import com.brg.domain.DatabaseType;
import com.brg.domain.RuleOperand;
import com.brg.domain.RuleValueBundle;

public interface ExportTemplate {
    String fillTemplate(RuleValueBundle bundle, RuleOperand operand, String code);

    DatabaseType getType();

    void setCode(String code);
    String getCode();

    String getRuleClass();
    void setRuleClass(String clazz);

    String getSource();
    void setSource(String source);
}
