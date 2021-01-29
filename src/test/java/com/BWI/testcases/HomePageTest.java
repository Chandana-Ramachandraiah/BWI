package com.BWI.testcases;
import com.BWI.pages.BasePage;
import com.BWI.pages.HomePage;
import com.BWI.pages.Reusable;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 *  This page is used to writing testcase for homepage
 * @author Chandana
 * @version 1.0
 * @since 28-01-2021
 */
public class HomePageTest extends BasePage {
    String sheetName = "Data";
    HomePage homepage;
    Reusable reusable;
    public HomePageTest() {
        super();
    }

    @DataProvider(name="getBWITestData",indices = {0})
    public Object[][] getBWITestData(){
        Object data[][] = Reusable.getTestData(sheetName);
        return data;
    }

    @Test(priority = 0,dataProvider="getBWITestData")
    public void BWITestMethod(String destination, String checkInDate, String checkOutDate) {

        String[] CheckinDate1= Reusable.splitFunction(checkInDate);
        String[] CheckoutDate1= Reusable.splitFunction(checkOutDate);
       // HomePage.initialize();
        homepage = new HomePage();
        reusable = new Reusable();
        String getTitle= homepage.homePageTitle();
        Assert.assertEquals(getTitle,"Book Direct at Best Western Hotels & Resorts");
        homepage.enterDestinationInput(destination);
        homepage.ClickOnCheckIn();
        homepage.selectMonth(CheckinDate1[1]);
        homepage.selectDate(CheckinDate1[0]);
        homepage.clickOnCheckOut();
        homepage.selectMonth(CheckoutDate1[1]);
        homepage.selectDate(CheckoutDate1[0]);
        homepage.clickOnAccept();
        String testOfFindMyHotel = homepage.getHotelName();
        Assert.assertEquals(testOfFindMyHotel,"FIND MY HOTEL");
        homepage.clickOnFindMyHotel();
        reusable.waitTillDocumentLoads();
        reusable.verifyDestinationAndDates(destination,checkInDate,checkOutDate);




    }



}
