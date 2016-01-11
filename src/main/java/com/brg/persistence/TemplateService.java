package com.brg.persistence;

import com.brg.domain.DatabaseType;
import com.brg.generate.ExportTemplate;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

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
        private String template;
        private String name;
        private String type;
        private String code;

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
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
     * @param template Template object from json
     */
    private void registerTemplate(JsonTemplate template) {
        System.out.println(template.getTemplate());

        // TODO: Convert to MySQLExport or OracleExport and save it in the local templates hashmap with the Type as key.
    }
}
