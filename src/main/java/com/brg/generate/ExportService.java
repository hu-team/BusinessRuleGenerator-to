package com.brg.generate;

import com.brg.domain.BusinessRule;

public interface ExportService {
    Export createExport(BusinessRule rule) throws ClassNotFoundException;
}
