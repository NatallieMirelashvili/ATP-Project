package Server;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class Configurations {
    // Singleton design pattern
    private static Configurations configuration;
    private final Properties properties;
    private static final String CONFIG_FILE_NAME = "config.properties";

    private Configurations() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE_NAME)) {
            if (input == null) {
                System.out.println("Sorry, unable to find properties.config");
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Configurations getInstance() {
        if (configuration == null) {
            configuration = new Configurations();
        }
        return configuration;
    }

//    // Save properties to the file
//    public void store() {
//        try {
//            String configFilePath = getClass().getClassLoader().getResource("config.properties").getPath();
//            properties.store(new FileOutputStream(configFilePath), null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public void store() {
        try {
            URL resourceUrl = getClass().getClassLoader().getResource(CONFIG_FILE_NAME);
            if (resourceUrl == null) {
                throw new FileNotFoundException("config.properties not found in the classpath.");
            }
            String configFilePath = new File(resourceUrl.toURI()).getPath();
            properties.store(new FileOutputStream(configFilePath), null);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


    // Setting default properties
    private void createDefaultProperties() {
        setProperty("threadPoolSize", "4");
        setProperty("mazeGeneratingAlgorithm", "MyMazeGenerator");
        setProperty("mazeSearchingAlgorithm", "BestFirstSearch");
    }

    // Get properties
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    // Set a property value
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        store();
    }

    // Remove a property
    public void removeProperty(String key) {
        properties.remove(key);
        store();
    }

    public static void main(String[] args) {
        Configurations configuration = Configurations.getInstance();
     //   configuration.createDefaultProperties();
        System.out.println(configuration.getProperty("mazeGeneratingAlgorithm", null));
    }
}
