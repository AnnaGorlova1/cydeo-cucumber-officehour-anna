package com.cydeo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {

    private Driver(){}

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>(); //parallel run


    public static WebDriver getDriver(){
      //  if(driver == null){ //single run
        if(driverPool.get() == null){ //parallel run
            String browserType = ConfigurationReader.getProperty("browser");

            switch (browserType){
                case "chrome":
                    //WebDriverManager.chromedriver().setup();
                    //driver = new ChromeDriver(); //single run
                    driverPool.set(new ChromeDriver()); //parallel run
                    //driver.manage().window().maximize(); //single run
                    driverPool.get().manage().window().maximize(); //parallel run
                    //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "firefox":
                    //WebDriverManager.firefoxdriver().setup();
                    //driver = new FirefoxDriver(); //single run
                    driverPool.set(new FirefoxDriver()); //parallel run
                    //driver.manage().window().maximize(); //single run
                    driverPool.get().manage().window().maximize(); //parallel run
                    //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //single run
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //parallel run
                    break;
            }
        }
        return driverPool.get();
    }

    /*
      Create a new Driver.closeDriver(); it will use .quit() method to quit browsers, and then set the driver value back to null.
       */
    public static void closeDriver(){
        //if (driver!=null){
        if (driverPool.get()!=null){
            /*
            This line will terminate the currently existing driver completely. It will not exist going forward.
             */
            //driver.quit();
            driverPool.get().quit();
            /*
            We assign the value back to "null" so that my "singleton" can create a newer one if needed.
             */
            //driver = null;
            driverPool.remove();
        }
    }



}
