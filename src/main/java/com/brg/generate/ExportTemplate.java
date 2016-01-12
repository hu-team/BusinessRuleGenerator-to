package com.brg.generate;

import com.brg.domain.DatabaseType;
import com.brg.domain.RuleOperand;
import com.brg.domain.RuleValueBundle;
import org.stringtemplate.v4.ST;

import java.net.InetSocketAddress;

public interface ExportTemplate {
    String fillTemplate(RuleValueBundle bundle, RuleOperand operand);

    DatabaseType getType();

    void setCode(String code);

    String getCode();
    String getSource();

    void setTemplate(ST template);
    ST getTemplate();

    String getRuleClass();
    void setRuleClass(String clazz);
}
