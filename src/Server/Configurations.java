package Server;

import java.io.*;
import java.util.Properties;

public class Configurations {
    // Singleton design pattern
    private static Configurations configuration;
    private final Properties properties;
    private static final String CONFIG_FILE_NAME ="resources/config.properties";

    private Configurations() {
        properties = new Properties();

    }
    public static Configurations getInstance() {
        if (configuration == null) {
            configuration = new Configurations();
        }
        return configuration;
    }


    // Get properties
    public String getProperty(String key) {
        try {
            InputStream input = new FileInputStream(CONFIG_FILE_NAME);
            properties.load(input);
        } catch (IOException e) {
           e.printStackTrace();
        }
        return properties.getProperty(key);
    }
    // Setting default properties
    private void createDefaultProperties() {
        setProperty("threadPoolSize", "4");
        setProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
        setProperty("mazeSearchingAlgorithm", "BestFirstSearch");
    }

    // Set a property value
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);

    }

    // Remove a property
    public void removeProperty(String key) {
        properties.remove(key);
    }

}
