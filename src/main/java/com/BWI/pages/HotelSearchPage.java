package com.BWI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

    @FindBy(css = "aspectMaintainer")
    private List<WebElement> hotelSearchCard;

    @FindBy(css = ".hotelName.hotelNameLink")
    private List<WebElement> hotelName;

    @FindBy(id = "btn-modify-stay")
    private WebElement changeSearchButton;

    @FindBy(id = "btn-modify-stay-update")
    private WebElement updateButton;

    /**usage: Taking destination summary.
     * author= Chandana
     * version 1.8.0_121
     */
    public String destinationSummary() {
        System.out.println(destinationSummaryField.getText());
        return destinationSummaryField.getText();

    }
    /**usage: Getting summary checkin.
     * author= Chandana
     * version 1.8.0_121
     */
    public WebElement getSummaryCheckin() {
        return summaryCheckInField;
    }
    /**usage: Getting summary checkout.
     * author= Chandana
     * version 1.8.0_121
     */
    public WebElement getSummaryCheckOut() {
        return summaryCheckOutField;
    }
    /**usage: Getting total cards.
     * author= Chandana
     * version 1.8.0_121
     */
    public int getHotelSearchCards() {
        //get cards
        int count =hotelSearchCard.size();
        return count;

    }
    /**usage: Getting hotel name.
     * author= Chandana
     * version 1.8.0_121
     */
    public void getHotelName(){
        int count =hotelName.size();
        for(int i=0;i<count;i++)
        {
            System.out.println("Hotels are "+hotelName.get(i).getText());
        }

    }

    /**usage: To click on change search.
     * author= Chandana
     * version 1.8.0_121
     */
    public WebElement clickOnChangeSearch() {
        return changeSearchButton;
    }

    /**usage: To click on Update.
     * author= Chandana
     * version 1.8.0_121
     */
    public void clickOnUpdate() {
        updateButton.click();

    }


}
