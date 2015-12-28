package com.brg.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Config instance;

    private Properties properties;

    public static Config getInstance() throws IOException {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private Config() throws IOException {
        this.properties = new Properties();

        // Load config from resource
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");

        if (inputStream != null) {
            properties.load(inputStream);
        }else{
            throw new FileNotFoundException("property file 'config.properties' not found in the classpath");
        }

    }


    public Properties getConfig() {
        return properties;
    }
}
