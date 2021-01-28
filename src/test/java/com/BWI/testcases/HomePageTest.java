package com.BWI.testcases;

import com.BWI.pages.BasePage;
import com.BWI.pages.HomePage;
import com.BWI.pages.Reusable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BasePage {
    public static String  destinationinputdata;
    public String checkindate;
    public String checkoutdate;
    String sheetName = "InputData";
    //UpdateInputData


    public HomePageTest() {
        super();
    }
    HomePage homepage;

    @DataProvider
    public Object[][] getBWITestData(){
        Object data[][] = Reusable.getTestData(sheetName);
        return data;
    }
    @Test(priority = 0,dataProvider="getBWITestData")
    public void BWITestmethod(String destination,String CheckinDate, String CheckoutDate) {
        String[] CheckinDate1= Reusable.splitfunction(CheckinDate);
        String[] CheckoutDate1= Reusable.splitfunction(CheckoutDate);
        HomePage.initialize();
        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        homepage = new HomePage();
        String getTitle= homepage.homePageTitle();

        Assert.assertEquals(getTitle,"Book Direct at Best Western Hotels & Resorts");
        destinationinputdata= homepage.enterDestinationInput(destination);

        WebElement checkinDatefield = homepage.ClickOnCheckIn();
       checkindate=checkinDatefield.getText();
        homepage.selectMonth(CheckinDate1[1]);
        homepage.selectdate(CheckinDate1[0]);

        WebElement checkoutdatefield = homepage.clickOnCheckout();
        checkoutdate=checkoutdatefield.getText();
        homepage.selectMonth(CheckoutDate1[1]);
        homepage.selectdate(CheckoutDate1[0]);
        String testoffindmyhotel = homepage.findmyhotel().getText();
        homepage.clickOnAccept();

        Assert.assertEquals(testoffindmyhotel,"FIND MY HOTEL");

        homepage.findmyhotel().click();



    }



}
