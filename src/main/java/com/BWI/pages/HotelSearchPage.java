package com.BWI.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


/**
 *  This page is used for elements in search hotel page
 * author=Chandana
 *
 */
public class HotelSearchPage extends BasePage{

    public HotelSearchPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "p#summary-destination")
    private WebElement destinationSummaryField;

    @FindBy(css = "p#summary-checkin")
    private WebElement summaryCheckInField;

    @FindBy(css = "p#summary-checkout")
    private WebElement summaryCheckOutField;

    @FindBy(css = ".aspectMaintainer")
    private List<WebElement> hotelSearchCard;

    @FindBy(css = ".hotelName.hotelNameLink")
    private List<WebElement> hotelName;

    @FindBy(id = "btn-modify-stay")
    private WebElement changeSearchButton;

    @FindBy(id = "btn-modify-stay-update")
    private WebElement updateButton;
    Reusable reuse = new Reusable();

    public String getTitle(){
         return driver.getTitle();
    }

    public String destinationSummary() {
        return destinationSummaryField.getText();
    }

    public String getSummaryCheckin() {
        return summaryCheckInField.getText();
    }

    public String getSummaryCheckOut() {
        return summaryCheckOutField.getText();
    }

    public int getHotelSearchCards() {
        //get cards
        reuse.waitTillDocumentLoads();
        int count =hotelSearchCard.size();
        return count;
    }

    public void getHotelName(){
        int count =hotelName.size();
        for(int i=0;i<count;i++)
        {
            System.out.println("Hotel "+(i+1)+" is "+hotelName.get(i).getText());
        }
        System.out.println(count);

    }

    public void clickOnChangeSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(changeSearchButton));
        changeSearchButton.click();
    }

    public void clickOnUpdate() {
        wait.until(ExpectedConditions.elementToBeClickable(updateButton));
        updateButton.click();

    }


}
