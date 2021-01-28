package com.BWI.pages;

import com.BWI.utils.WebEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.GuiceHelper;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {
    public static WebDriver driver;
    public static Properties prop;
    public  static EventFiringWebDriver e_driver;
    public  static WebDriverWait wait;
    public static SoftAssert softassertion;
    //public static WebDriverEventListener eventListener;


    public  BasePage() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream("C://Users//cramachandraia//IdeaProjects//com.BestWestern//BWI//src//main//resources//Configurations.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialize() {
        String browserName = prop.getProperty("browser");

        System.out.println(prop.getProperty("browser"));
        softassertion = new SoftAssert();

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver","D://Selenium//chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equals("ff")) {

            WebDriverManager.firefoxdriver().setup();
            WebDriver driver = new FirefoxDriver();
        } else {
            System.out.println("Browser not found");
        }

      /*  e_driver = new EventFiringWebDriver(driver);
        // Now create object of EventListerHandler to register it with EventFiringWebDriver
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;
*/
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        wait =new WebDriverWait(driver,50);

        driver.get(prop.getProperty("url"));

        System.out.println(prop.getProperty("url"));

    }


}
