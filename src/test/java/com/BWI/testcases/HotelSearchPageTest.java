package com.BWI.testcases;

import com.BWI.pages.HomePage;
import com.BWI.pages.HotelSearchPage;
import com.BWI.pages.Reusable;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.internal.support.FileReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.nashorn.internal.parser.JSONParser;
import org.openqa.selenium.JavascriptExecutor;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class HotelSearchPageTest extends  HomePageTest {
    HotelSearchPage hotelSearch;
    HomePage homePage;

    public static String destinationInputData;
    public String checkInDate;
    public String checkOutDate;
    String sheetName = "Sheet1";

    public HotelSearchPageTest() {
        super();
    }
    @DataProvider(name="getBWITestDataUpdate",indices = {1})
    public Object[][] getBWITestDataUpdate(){
        Object data[][] = Reusable.getTestData(sheetName);
        return data;
    }
    /**
     * @param destination
     * @param checkInDate
     * @param checkOutDate
     */
    @Test(priority = 1,dataProvider="getBWITestDataUpdate")
    public void findHotel(String destination, String checkInDate, String checkOutDate) {
    //Take data from excel and split
        String[] CheckinDate1= Reusable.splitFunction(checkInDate);
        String[] CheckoutDate1= Reusable.splitFunction(checkOutDate);
    //Create object of class
        hotelSearch = new HotelSearchPage();
        homePage = new HomePage();
        Reusable verify = new Reusable();

        //verify cards are present
        int count;
        count=hotelSearch.getHotelSearchCards();
        if (count>0) {
            System.out.println("Hotel Cards Displayed");

        } else {
            System.out.println("please change you search");
        }

        hotelSearch.clickOnChangeSearch().click();
        homePage.enterDestinationInput(destination);
        //select date for checkin
       homePage.ClickOnCheckIn();
        homePage.selectMonth(CheckinDate1[1]);
        homePage.selectDate(CheckinDate1[0]);
        //select date for checkin
         homePage.clickOnCheckout();
        homePage.selectMonth(CheckoutDate1[1]);
        homePage.selectDate(CheckoutDate1[0]);
        hotelSearch.clickOnUpdate();

        //wait till document is ready.
        wait.until( webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        //Verify destination after changing.
        verify.verifyDestinationAndDates(destination,checkInDate,checkOutDate);
        //Printing hotelNames
        hotelSearch.getHotelName();

        softAssertion.assertAll();
        //===========Rest Assured==============
        //base URI
        RestAssured.baseURI="https://www.bestwestern.com/bin/bestwestern/proxy?gwServiceURL=HOTEL_SEARCH&distance=50&depth=2&checkinDate=2021-02-20&checkoutDate=2021-02-23&latitude=41.8781136&longitude=-87.6297982&numberOfRooms=1&occupant=numAdults:1,numChild:0&chain=BW&chain=UR&chain=PB&chain=XW";
        //Request
        driver.navigate().refresh();
        ///bin/bestwestern/proxy?gwServiceURL=HOTEL_SEARCH&distance=50&depth=2&checkinDate=2021-02-23&checkoutDate=2021-02-27";
        RequestSpecification requestHit= RestAssured.given();
        //=============== Get method ========================================
      //Response
        Response response= requestHit.request(Method.GET);
        requestHit.header("Content-Type", "application/json;charset=utf-8");
        //See Response body

        String responseBody= response.getBody().asString();

       /* // parsing file "JSONExample.json"
        Object obj = new JSONParser().parse(new FileReader("response.getBody()"));

        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;
        JsonPath j1= new JsonPath(response.getBody());*/

        //Get values from Response
        int statusCode= response.getStatusCode();
        System.out.println("Response code is "+ statusCode);

    }



}


