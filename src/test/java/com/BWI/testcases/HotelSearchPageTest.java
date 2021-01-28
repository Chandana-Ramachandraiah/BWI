package com.BWI.testcases;

import com.BWI.pages.BasePage;
import com.BWI.pages.HomePage;
import com.BWI.pages.HotelSearchPage;
import com.BWI.pages.Reusable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HotelSearchPageTest extends  HomePageTest {
    HotelSearchPage hotelsearch;
    HomePage homepage;
    public static String  destinationinputdata;
    public String checkindate;
    public String checkoutdate;
    String sheetName = "InputData";
    public HotelSearchPageTest() {
        super();

    }


    @Test(priority = 1)
    public void findHotel() throws InterruptedException {
        hotelsearch = new HotelSearchPage();
        homepage = new HomePage();
        HotelSearchPageTest verify = new HotelSearchPageTest();
        verify.verifyDestinationandDates();
        

        if (hotelsearch.getHotelSearchcard().isDisplayed()) {
            System.out.println("yeah displayed");

        } else {
            System.out.println("please change you search");
        }

        hotelsearch.clickOnChangeSearch().click();
        homepage.enterDestinationInput("Ustroń, Poland");

        WebElement checkinDatefield = homepage.ClickOnCheckIn();
        checkindate = checkinDatefield.getText();
        homepage.selectMonth("May");
        homepage.selectdate("28");

        WebElement checkoutdatefield = homepage.clickOnCheckout();
        checkoutdate = checkoutdatefield.getText();
        homepage.selectMonth("June");
        homepage.selectdate("1");
        hotelsearch.clickOnUpdate();
        verify.verifyDestinationandDates();



        //softassertion.assertAll();

    }

    public void verifyDestinationandDates() {

        hotelsearch = new HotelSearchPage();
        homepage = new HomePage();
        String destinationsummary = hotelsearch.destinationSummary();
        System.out.println(destinationsummary);
        softassertion.assertEquals(destinationsummary, destinationinputdata);
        System.out.println(destinationsummary);
        String summarycheckin = hotelsearch.getsummaryCheckin().getText();
        try {
            softassertion.assertEquals(summarycheckin, checkindate);

        } catch (
                Error e) {
            softassertion.assertAll();
        }

        String summarycheckout = hotelsearch.getsummaryCheckin().getText();
        softassertion.assertEquals(summarycheckout, checkoutdate);
    }
}


