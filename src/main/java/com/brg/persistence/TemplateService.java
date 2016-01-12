package com.brg.persistence;

import com.brg.domain.DatabaseType;
import com.brg.generate.ExportTemplate;
import com.brg.generate.MySQLExport;
import com.brg.generate.OracleExport;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.stringtemplate.v4.ST;

import java.io.InputStreamReader;
import java.util.HashMap;

public class TemplateService {
    /**
     * Export Template Holder
     */
    private HashMap<DatabaseType, ExportTemplate> templates = new HashMap<DatabaseType, ExportTemplate>();

    public TemplateService() {
        // Load templates from disk
        try {
            this.loadTemplates();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The fetching object
     */
    private class JsonTemplate {
        private String[] template;
        private String name;
        private String type;
        private String code;

        public String[] getTemplate() {
            return template;
        }

        public void setTemplate(String[] template) {
            this.template = template;
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
    }


    /**
     * (Re)load templates from disk
     */
    public void loadTemplates() throws Exception {
        // Clear the current templates first
        this.templates.clear();

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

            // Read the file, and make and register the template
            if (templateJson != null) {
                this.registerTemplate(templateJson);
            }
        }
    }


    /**
     * Register template from Json into the local Service
     * @param jsonTemplate Template object from json
     */
    private void registerTemplate(JsonTemplate jsonTemplate) {
        String stringTemplate = String.join("\n", jsonTemplate.getTemplate());

        ExportTemplate template = null;
        DatabaseType type = null;

        if("MYSQL".equals(jsonTemplate.getType())){
            type = DatabaseType.MYSQL;
            template = new MySQLExport();

        } else if("ORACLE".equals(jsonTemplate.getType())){
            type = DatabaseType.ORACLE;
            template = new OracleExport();
        }

        template.setTemplate(new ST(stringTemplate));
        template.setCode(jsonTemplate.getCode());

        this.templates.put(type, template);
    }
}
