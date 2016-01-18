package com.brg.persistence;

import com.brg.common.AbstractFacadeService;
import com.brg.domain.BusinessRule;
import com.brg.domain.RuleOperand;
import com.brg.domain.RuleValueBundle;
import com.brg.generate.ExportTemplate;

import java.util.List;

public class PersistenceServiceImpl extends AbstractFacadeService implements PersistenceService {

    private BusinessRuleService businessRuleService = new BusinessRuleService();
    private RuleValueBundleService ruleValueBundleService = new RuleValueBundleService();
    private RuleOperandService ruleOperandService = new RuleOperandService();
    private TemplateService templateService = new TemplateService();


    @Override
    public ExportTemplate getTemplateForBundle(BusinessRule rule) throws Exception {
        return this.templateService.getTemplateForBundle(rule);
    }

    @Override
    public RuleOperand getRuleOperandById(int id) {
        return this.ruleOperandService.getRuleOperandById(id);
    }

    @Override
    public RuleValueBundle getRuleValueBundleById(int id) {
        return this.ruleValueBundleService.getRuleById(id);
    }

    @Override
    public void reloadRules() {
        this.businessRuleService.reloadRules();
    }

    @Override
    public List<BusinessRule> getAllRules() {
        return this.businessRuleService.getRules();
    }


}
