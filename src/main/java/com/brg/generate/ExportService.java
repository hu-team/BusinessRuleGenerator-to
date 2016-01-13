package com.brg.generate;

import com.brg.ServiceProvider;
import com.brg.domain.BusinessRule;

public class ExportService implements ExportServiceImpl {
    @Override
    public Export createExport(BusinessRule rule) throws ClassNotFoundException {
        if (rule == null) {return null;}

        // Decide the template
        ExportTemplate template = null;
        try {
            template = ServiceProvider.getInstance().getPersistenceService().getTemplateService().getTemplateForBundle(rule);
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
}
