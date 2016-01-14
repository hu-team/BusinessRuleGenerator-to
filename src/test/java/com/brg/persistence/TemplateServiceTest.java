package com.brg.persistence;

import com.brg.domain.BusinessRule;
import com.brg.domain.DatabaseType;
import com.brg.domain.rules.*;
import com.brg.generate.ExportTemplate;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;


public class TemplateServiceTest {

    private HashMap<DatabaseType, List<ExportTemplate>> templates = new HashMap<DatabaseType, List<ExportTemplate>>();
    private TemplateService templateService;
    private BusinessRule AttributeCompareRule, AttributeListRule, AttributeOtherRule, AttributeRangeRule, EntityOtherRule, InterEntityCompareRule, ModifyRule, TupleCompareRule, TupleOtherRule;

    @Before
    public void setUp() throws Exception {
        templateService = new TemplateService();
        AttributeCompareRule = new AttributeCompareRule();
        AttributeListRule = new AttributeListRule();
        AttributeOtherRule = new AttributeOtherRule();
        AttributeRangeRule = new AttributeRangeRule();
        EntityOtherRule = new EntityOtherRule();
        InterEntityCompareRule = new InterEntityCompareRule();
        ModifyRule = new ModifyRule();
        TupleCompareRule = new TupleCompareRule();
        TupleOtherRule = new TupleOtherRule();
    }

    @Test
    public void testLoadTemplates() throws Exception {
        assertEquals("ACMP", templateService.getTemplateForBundle(AttributeCompareRule).getCode());
        assertEquals("ALIS", templateService.getTemplateForBundle(AttributeListRule).getCode());
        assertEquals("AOTH", templateService.getTemplateForBundle(AttributeOtherRule).getCode());
        assertEquals("ARNG", templateService.getTemplateForBundle(AttributeRangeRule).getCode());
        assertEquals("EOTH", templateService.getTemplateForBundle(EntityOtherRule).getCode());
        assertEquals("ICMP", templateService.getTemplateForBundle(InterEntityCompareRule).getCode());
        assertEquals("MODI", templateService.getTemplateForBundle(ModifyRule).getCode());
        assertEquals("TCMP", templateService.getTemplateForBundle(TupleCompareRule).getCode());
        assertEquals("TOTH", templateService.getTemplateForBundle(TupleOtherRule).getCode());
    }
}