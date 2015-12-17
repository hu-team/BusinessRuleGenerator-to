package com.brg.generate;

import com.brg.domain.BusinessRule;

public class ExportService implements ExportServiceImpl {
    @Override
    public ExportTemplate createExport(BusinessRule rule) {
        if (rule == null) {return null;}

        // TODO: Dynamic database types
        return new OracleExport(rule.getValues());
    }
}
