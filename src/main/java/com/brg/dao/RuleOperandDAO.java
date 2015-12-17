package com.brg.dao;

import com.brg.ServiceProvider;
import com.brg.domain.RuleOperand;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class RuleOperandDAO implements DAO {

    @Override
    public ArrayList<RuleOperand> executeRead(String sql) {
        ArrayList<RuleOperand> operands = new ArrayList<RuleOperand>();

        try{
            Statement stmt = ServiceProvider.getInstance().getPersistenceService().getConnection().createStatement();
            ResultSet set = stmt.executeQuery(sql);

            while(set.next()){
                RuleOperand operand = new RuleOperand();
                operand.setSign(set.getString("SIGN"));
                operands.add(operand);
            }

            set.close();
            stmt.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return operands;
    }

    public RuleOperand getRuleOperandById(int id){
        ArrayList<RuleOperand> operands = this.executeRead("SELECT * FROM RULEOPERAND WHERE RULEOPERANDID= " + id);
        return operands.size() >= 1 ? operands.get(0) : null;
    }

    public ArrayList<RuleOperand> getAllRuleOperand(){
        return this.executeRead("SELECT * FROM RULEOPERAND");
    }

}
