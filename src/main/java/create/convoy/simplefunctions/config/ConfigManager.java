package create.convoy.simplefunctions.config;

import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.yaml.snakeyaml.Yaml;

import create.convoy.simplefunctions.Playercommands;

public class ConfigManager {
    private String configFileLocation;
    private String configFileName;
    private String defaultConfigFilePath = "assets/simplefunctions/default_config.yaml";
    private File configDir;
    private File configFile;

    public String getConfigFileLocation() {return configFileLocation;}
    public String getConfigFileName() {return configFileName;}
    public File getConfigDir() {return configDir;}
    public File getConfigFile() {return configFile;}

    public String getDefaultConfigFilePath() {return defaultConfigFilePath;}
    public void setDefaultConfigFilePath(String DefaultConfigFilePath) {defaultConfigFilePath = DefaultConfigFilePath;}

    public ConfigManager(String ConfigFileLocation, String ConfigFileName, String DefaultConfigFilePath) {
        configFileLocation = ConfigFileLocation;
        configFileName = ConfigFileName;
        defaultConfigFilePath = DefaultConfigFilePath;
        configDir = new File(configFileLocation);

        if (!configDir.exists()) {
            boolean newDir = configDir.mkdirs();

            if (newDir) {
                Playercommands.LOGGER.info(Playercommands.MOD_NAME + ": " + "Successfully Created Config Directory");
            } else {
                Playercommands.LOGGER.error(Playercommands.MOD_NAME + ": " + "Failed to Create Config Directory");
                return;
            }
        }

        configFile = new File(configFileLocation + configFileName);
        try {
            if (configFile.createNewFile()) {
                Playercommands.LOGGER.info(Playercommands.MOD_NAME + ": " + "File created: " + configFile.getPath());
                
                copyFile(this.getClass().getClassLoader().getResourceAsStream(defaultConfigFilePath), configFile);
            } else {
                Playercommands.LOGGER.info(Playercommands.MOD_NAME + ": " + "Config file already exists.");
            }
        } catch (IOException e) {
            Playercommands.LOGGER.info(Playercommands.MOD_NAME + ": " + "An error occurred.");
            e.printStackTrace();
        }
    }

    public Map<String, Object> getConfig() {
        Yaml yaml = new Yaml();

        try {
            FileInputStream inputStream = new FileInputStream(configFile);
            return yaml.load(inputStream);
        } catch (Exception e) {
            Map<String, Object> returnMap = Map.of();
            return returnMap;
        }
    }

    public Map<String, Object> getDefaultConfig() {
        Yaml yaml = new Yaml();
        InputStream inputStream = ConfigManager.class.getResourceAsStream(defaultConfigFilePath);
        return yaml.load(inputStream);
    }

    public void saveConfig() {}

    private void copyFile(InputStream sourceFile, File writeFile) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(writeFile);
        try { 
            int i; 
            while ((i = sourceFile.read()) != -1) {
                outputStream.write(i); 
            } 
        } finally { 
            if (sourceFile != null) {
                sourceFile.close(); 
            }
            if (outputStream != null) { 
                outputStream.close(); 
            } 
        }
        Playercommands.LOGGER.info("Successfully Copied File");
    } 
}