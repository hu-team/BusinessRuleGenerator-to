package com.brg.persistence;


import com.brg.common.AbstractFacadeService;

public class PersistanceService extends AbstractFacadeService implements PersistanceServiceImpl {

    private BusinessRuleService businessRuleService = new BusinessRuleService();

    @Override
    public BusinessRuleService getBusinessRuleService() {
        return this.businessRuleService;
    }
}
