package com.BWI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {
    /**
     * Usage: constructor
     * @author Chandana
     */
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "destination-input")
    private WebElement destinationInputTextBox;

    @FindBy(css = "input#checkin")
    private WebElement checkin;

    @FindBy(css = "td[data-handler='selectDay']")
    private List<WebElement> checkInDates;

    @FindBy(css = ".ui-datepicker-month")
    private WebElement checkInMonth;

    @FindBy(css = ".ui-datepicker-next.ui-corner-all")
    private WebElement next;

    @FindBy(css = "input#checkout")
    private WebElement checkout;

    @FindBy(css = "td[data-handler='selectDay']")
    private List<WebElement> checkOutDates;

    @FindBy(css = ".ui-datepicker-month")
    private WebElement checkOutMonth;

    @FindBy(css = ".ui-datepicker-next.ui-corner-all")
    private WebElement checkOutNext;

    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement accept;

    @FindBy(id = "btn-modify-stay-update")
    private WebElement findMyHotelButton;

    public String homePageTitle(){
       return driver.getTitle();
    }
    /**
     * Usage: to enter destination
     * @author Chandana
     * version 1.8.0_121
     */
    public void enterDestinationInput(String destination) {
        destinationInputTextBox.clear();
        destinationInputTextBox.sendKeys(destination);
        WebElement autoSuggestiveDestination;

        try{
             autoSuggestiveDestination=driver.findElement(By.xpath("//ul[@id='google-suggestions']//li[@data-place='" + destination + "']"));

            autoSuggestiveDestination.click();

        }catch(StaleElementReferenceException e) {
             autoSuggestiveDestination=driver.findElement(By.xpath("//ul[@id='google-suggestions']//li[@data-place='" + destination + "']"));
            autoSuggestiveDestination.click();
        }


    }
    
    public void ClickOnCheckIn() {
        checkin.click();
    }

    /**usage: for selecting month.
     * author= Chandana
     * @param month
     * version 1.8.0_121
     */
    public void selectMonth(String month)  {
        //click on next until you get desired month
       while(!checkInMonth.getText().contains(month)) {
           try {
               Thread.sleep(2000);
               next.click();
           }catch(StaleElementReferenceException | InterruptedException e){
               next.click();
           }
       }
    }
    /**usage: for selecting month.
     * author= Chandana
     * @param date
     * version 1.8.0_121
     */
    public void selectDate(String date) {
        //Grab common attribute ,Put into list and iterate for seleting date
       int checkInDateSize = checkInDates.size();
       for (int i = 0; i < checkInDateSize; i++) {
            String text = checkInDates.get(i).getText();
            if (text.equalsIgnoreCase(date)) {
                checkInDates.get(i).click();
                break;
            }
        }
    }

    /**usage: clicking on CheckOut.
     * author= Chandana
     *
     * version 1.8.0_121
     */
    public void clickOnCheckout(){
        try{
            checkout.click();
        }
        catch(StaleElementReferenceException e){
            checkout.click();
        }

    }
    /**usage: clicking on CheckOut.
     * author= Chandana
     *
     * version 1.8.0_121
     */
    public void clickOnAccept() {
        accept.click();

    }
    /**usage: returning webElement.
     * author= Chandana
     *
     * version 1.8.0_121
     */
    public WebElement findMyHotel() {
       return findMyHotelButton;
    }

}
