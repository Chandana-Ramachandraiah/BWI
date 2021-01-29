package com.BWI.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;
import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 *  This page is used for driver  and passing url
 * author=Chandana
 *
 */

public class BasePage  {
    public static WebDriver driver;
    public static Properties prop;
    public  static WebDriverWait wait;
    public static SoftAssert softAssertion;


    public  BasePage() {

        //Take data from Properties File
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("C://Users//cramachandraia//IdeaProjects//com.BestWestern//BWI//src//main//resources//Configurations.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @BeforeTest
    //.static void
    public void initialize() {
        //Initialize particular browser url according to the data given in Properties file
        String browserName = prop.getProperty("browser");

        prop.getProperty("browser");
        softAssertion = new SoftAssert();

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",".//src//main//resources//driver//chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equals("ff")) {

            WebDriverManager.firefoxdriver().setup();
            WebDriver driver = new FirefoxDriver();
        } else {
            System.out.println("Browser not found");
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(360, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        wait =new WebDriverWait(driver,50);

        //and give url according to the data given in Properties file
        driver.get(prop.getProperty("url"));

    }

    @AfterTest
    public void tearDown()
    {
        driver.close();
        driver.quit();
    }


}
