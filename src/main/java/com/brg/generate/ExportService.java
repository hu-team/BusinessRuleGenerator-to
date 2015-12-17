package com.brg.generate;

import com.brg.domain.BusinessRule;
import com.brg.domain.DatabaseType;
import org.stringtemplate.v4.ST;

public class ExportService implements ExportServiceImpl {
    @Override
    public Export createExport(BusinessRule rule) {
        if (rule == null) {return null;}

        // TODO: Dynamic templates via dao
        ExportTemplate template = new OracleExport();
        template.setTemplate(new ST("declare\n" +
                "l_passed boolean := true;\n" +
                "begin\n" +
                "if l_oper in ( 'INS', 'UPD' )\n" +
                "then\n" +
                "l_passed := :new.<attribute_column> <operand> <compare_with>;\n" +
                "if not l_passed\n" +
                "then\n" +
                "l_error_stack := l_error_stack || 'De prijs van het product mag niet negatief zijn.';\n" +
                "end if;\n" +
                "end if;\n" +
                "end;"));

        Export export = new Export(rule);
        export.setTemplate(template);

        // TODO: Dynamic database types
        export.setDatabaseType(DatabaseType.ORACLE);


        export.setTriggerIdentifier(rule.getCode());

        return export;
    }
}
