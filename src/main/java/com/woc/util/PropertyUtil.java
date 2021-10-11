package com.woc.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    private static final Logger logger = LogManager.getLogger(PropertyUtil.class.getName());

    private static String fileLocation = "src/main/resources/config.properties";
    private static Properties properties;

    static{
        logger.info("going to load the config.properties file");
        properties = new Properties();

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileLocation);
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            logger.error("Could not find the file, please check the file location", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("Unable to read the content of config.properties file", e);
            throw new RuntimeException(e);
        }finally {
            if(null == fileReader){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    logger.warn("Unable to close the fileReader stream");
                }
            }
        }
        logger.info("config.properties file has been loaded successfully!");
    }

    public static String getProperty(String key){
        String value = properties.getProperty(key);
        return value;
    }



}
