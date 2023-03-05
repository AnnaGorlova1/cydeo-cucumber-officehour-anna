package com.cydeo.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    //create object
    //make it private, so it is not accessible outside the class
    private static Properties properties = new Properties();
    static {

        try {//open file
            FileInputStream file = new FileInputStream("configuration.properties");
            //load properties
            properties.load(file);
            file.close();

        } catch (IOException e) {
            System.out.println("FILE NOT FOUND WITH GIVEN PATH!!!");
            e.printStackTrace();
        }
    }

    //create utility method to use the object to read
    public static String getProperty(String keyword){
        return properties.getProperty(keyword);
    }

}
