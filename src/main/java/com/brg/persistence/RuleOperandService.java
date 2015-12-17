package com.brg.persistence;

import com.brg.ServiceProvider;
import com.brg.domain.RuleOperand;

import java.util.ArrayList;

/**
 * Developed by Arjan.
 */
public class RuleOperandService {

    private ArrayList<RuleOperand> operands;

    public ArrayList<RuleOperand> getOperands() {
        return this.operands;
    }

    public RuleOperand getRuleOperandById(int id){
        return ServiceProvider.getInstance().getDaoService().getRuleOperandDAO().getRuleOperandById(id);
    }
}
