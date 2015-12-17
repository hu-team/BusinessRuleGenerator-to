package com.brg.generate;

import com.brg.domain.BusinessRule;

public interface ExportServiceImpl {
    ExportTemplate createExport(BusinessRule rule);
}
