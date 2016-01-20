package com.brg.generate;

import com.brg.domain.BusinessRule;
import com.brg.domain.DatabaseType;

public interface ExportService {
    Export createExport(BusinessRule rule) throws ClassNotFoundException;

    ExportTemplate createTemplate(DatabaseType type);

    boolean applyExport(BusinessRule rule) throws Exception;
}
