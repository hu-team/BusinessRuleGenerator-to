package com.brg.generate;

import com.brg.ServiceProvider;
import com.brg.common.AbstractFacadeService;
import com.brg.domain.BusinessRule;
import com.brg.domain.DatabaseType;

public class ExportServiceImpl extends AbstractFacadeService implements ExportService {
    @Override
    public Export createExport(BusinessRule rule) throws ClassNotFoundException {
        if (rule == null) {return null;}

        // Decide the template
        ExportTemplate template = null;
        try {
            template = ServiceProvider.getInstance().getPersistenceService().getTemplateForBundle(rule);
        } catch (Exception e) {
            throw new ClassNotFoundException("No template class could be found for your business rule!");
        }

        // Make the export
        Export export = new Export(rule);
        export.setTemplate(template);
        export.setDatabaseType(template.getType());
        export.setTriggerIdentifier(rule.getCode());

        return export;
    }

    @Override
    public ExportTemplate createTemplate(DatabaseType type) {
        switch(type) {
            case MYSQL:
                return new MySQLExport();
            case ORACLE:
                return new OracleExport();
        }
        return null;
    }
}
