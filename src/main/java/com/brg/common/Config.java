package com.brg.common;

import com.brg.ServiceProvider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Config instance;

    private Properties properties;

    /**
     * Get Config instance
     * @return config instance
     * @throws IOException
     */
    public static Config getInstance() throws IOException {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    /**
     * Load config
     * @throws IOException
     */
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

    /**
     * Get Configuration Properties
     * @return properties
     */
    public Properties getConfig() {
        return properties;
    }

    /**
     * Reload Config and clear Database Connections
     * @throws IOException
     */
    public static void reloadConfig() throws IOException {
        instance = null;
        instance = new Config();

        // Clear connections
        ServiceProvider.getInstance().getDaoService().clearConnections();
    }
}
