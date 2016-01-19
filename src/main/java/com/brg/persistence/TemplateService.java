package com.brg.persistence;

import com.brg.ServiceProvider;
import com.brg.domain.BusinessRule;
import com.brg.domain.DatabaseType;
import com.brg.generate.*;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TemplateService {
    /**
     * Export Template Holder
     */
    private HashMap<DatabaseType, List<ExportTemplate>> templates = new HashMap<DatabaseType, List<ExportTemplate>>();


    public void reloadTemplatesFromDisk() {
        // Load templates from disk
        try {
            this.loadTemplates();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the template needed for the rule
     * @param rule Business rule given
     * @return template or null
     */
    public ExportTemplate getTemplateForBundle(BusinessRule rule) throws Exception {
        // Get target database type
        DatabaseType type = ServiceProvider.getInstance().getDaoService().getTargetConnection().getType();

        List<ExportTemplate> templates = this.templates.get(type);

        // Search for the correct
        for (ExportTemplate template : templates) {
            if (template.getRuleClass().equals(rule.getClass().getSimpleName())) {
                return template;
            }
        }

        return null;
    }

    /**
     * The fetching object
     */
    private class JsonTemplate {
        private String[] template;
        private String templateFile;
        private String name;
        private String type;
        private String code;
        private String clazz;

        public String[] getTemplate() {
            return template;
        }

        public void setTemplate(String[] template) {
            this.template = template;
        }

        public String getTemplateFile() {
            return templateFile;
        }

        public void setTemplateFile(String templateFile) {
            this.templateFile = templateFile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }
    }


    /**
     * (Re)load templates from disk
     */
    public void loadTemplates() throws Exception {
        // Clear the current templates first
        this.templates.clear();

        // Prepare the templates arrays
        this.templates.put(DatabaseType.MYSQL, new ArrayList<ExportTemplate>());
        this.templates.put(DatabaseType.ORACLE, new ArrayList<ExportTemplate>());

        // Read all files with templates
        JsonReader reader = new JsonReader(new InputStreamReader(getClass().getResource("/templates/contents.json").openStream(), "UTF-8"));

        reader.beginArray();

        // Read each file
        while(reader.hasNext()) {
            String templateFile = reader.nextString();

            // Read the template file
            InputStreamReader templateInputStream = new InputStreamReader(getClass().getResource("/templates/" + templateFile).openStream(), "UTF-8");
            Gson gson = new Gson();
            JsonTemplate templateJson = gson.fromJson(templateInputStream, JsonTemplate.class);

            // Inject code
            templateJson.setClazz(templateFile.substring(templateFile.indexOf('/') + 1, templateFile.length() - 5));

            // Read the file, and make and register the template
            this.registerTemplate(templateJson);
        }
    }


    /**
     * Register template from Json into the local Service
     * @param jsonTemplate Template object from json
     */
    private void registerTemplate(JsonTemplate jsonTemplate) {
        // Get the template itself
        String stringTemplate;

        if (jsonTemplate.getTemplate() == null && jsonTemplate.getTemplateFile() != null) {
            // Load from file
            try {
                File file = new File(getClass().getResource("/templates/" + jsonTemplate.getType().toLowerCase() + "/" + jsonTemplate.getTemplateFile()).getFile());
                stringTemplate = FileUtils.readFileToString(file, "UTF-8");
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return;
            }
        } else {
            stringTemplate = String.join("\n", jsonTemplate.getTemplate());
        }

        ExportTemplate template = null;
        DatabaseType type = null;

        DatabaseType[] types = DatabaseType.values();
        for (DatabaseType matchType: types) {
            if (jsonTemplate.getType().equals(matchType.name())) {
                type = matchType;
            }
        }

        if (type == null) {
            System.err.println("No type matched!");
            return;
        }


        template = ServiceProvider.getInstance().getExportService().createTemplate(type);

        template.setSource(stringTemplate);
        template.setCode(jsonTemplate.getCode());
        template.setRuleClass(jsonTemplate.getClazz());

        this.templates.get(type).add(template);
    }
}
