package com.brg.dao;

import com.brg.common.AbstractFacadeService;

public class DaoService extends AbstractFacadeService implements DaoServiceImpl {

    private BusinessRuleDAO businessRuleDAO = new BusinessRuleDAO();

    @Override
    public BusinessRuleDAO getBusinessRuleDAO() {
        return this.businessRuleDAO;
    }
}
