package com.brg.persistence;

import com.brg.domain.BusinessRule;
import com.brg.domain.RuleOperand;
import com.brg.domain.RuleValueBundle;
import com.brg.generate.ExportTemplate;

import java.util.List;

public interface PersistenceService {
    ExportTemplate getTemplateForBundle(BusinessRule rule) throws Exception;
    RuleOperand getRuleOperandById(int id);
    RuleValueBundle getRuleValueBundleById(int id);

    void reloadRules();

    List<BusinessRule> getAllRules();
}
