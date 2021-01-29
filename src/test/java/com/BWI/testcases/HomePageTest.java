package com.BWI.testcases;

import com.BWI.pages.BasePage;
import com.BWI.pages.HomePage;
import com.BWI.pages.Reusable;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HomePageTest extends BasePage {
    String sheetName = "Sheet1";
    //UpdateInputData


    public HomePageTest() {
        super();
    }
    HomePage homepage;

    @DataProvider(name="getBWITestData",indices = {0})
    public Object[][] getBWITestData(){
        Object data[][] = Reusable.getTestData(sheetName);
        return data;
    }

    /**
     * author=Chandana
     * @param destination
     * @param checkInDate
     * @param checkOutDate
     */
    @Test(priority = 0,dataProvider="getBWITestData")
    public void BWITestMethod(String destination, String checkInDate, String checkOutDate) {
        String[] CheckinDate1= Reusable.splitFunction(checkInDate);
        String[] CheckoutDate1= Reusable.splitFunction(checkOutDate);
        HomePage.initialize();
        wait.until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        homepage = new HomePage();
        String getTitle= homepage.homePageTitle();

        Assert.assertEquals(getTitle,"Book Direct at Best Western Hotels & Resorts");
        homepage.enterDestinationInput(destination);

        homepage.ClickOnCheckIn();

        homepage.selectMonth(CheckinDate1[1]);
        homepage.selectDate(CheckinDate1[0]);

        homepage.clickOnCheckout();

        homepage.selectMonth(CheckoutDate1[1]);
        homepage.selectDate(CheckoutDate1[0]);
        String testOfFindMyHotel = homepage.findMyHotel().getText();
        homepage.clickOnAccept();

        Assert.assertEquals(testOfFindMyHotel,"FIND MY HOTEL");
        homepage.findMyHotel().click();

        Reusable verify = new Reusable();

        verify.verifyDestinationAndDates(destination,checkInDate,checkOutDate);




    }



}
