package com.BWI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 *  This page is used for Initiation of all homepage elements.
 * @author Chandana
 * @version 1.0
 * @since 28-01-2021
 */
public class HomePage extends BasePage {

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "destination-input")
    private WebElement destinationInputTextBox;

    @FindBy(css = "input#checkin")
    private WebElement checkIn;

    @FindBy(css = "td[data-handler='selectDay']")
    private List<WebElement> checkInDates;

    @FindBy(css = ".ui-datepicker-month")
    private WebElement checkInMonth;

    @FindBy(css = ".ui-datepicker-next.ui-corner-all")
    private WebElement next;

    @FindBy(css = "input#checkout")
    private WebElement checkOut;

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
    WebDriverWait wait =new WebDriverWait(driver,120);
    public void enterDestinationInput(String destination) {
        destinationInputTextBox.clear();
        destinationInputTextBox.sendKeys(destination);
        WebElement autoSuggestiveDestination;

       try{
           autoSuggestiveDestination=driver.findElement(By.xpath("//ul[@id='google-suggestions']//li[@data-place='" + destination + "']"));
           autoSuggestiveDestination.click();
       }
       catch(StaleElementReferenceException e){
           autoSuggestiveDestination=driver.findElement(By.xpath("//ul[@id='google-suggestions']//li[@data-place='" + destination + "']"));
           autoSuggestiveDestination.click();

       }
    }
    
    public void ClickOnCheckIn() {
        checkIn.click();
    }

    public void selectMonth(String month)  {
        //click on next until you get desired month
       while(!checkInMonth.getText().contains(month)) {
            wait.until(ExpectedConditions.elementToBeClickable(next));
               next.click();
       }
    }

    public void selectDate(String date) {
        //Grab common attribute ,Put into list and iterate for selecting date
       int checkInDateSize = checkInDates.size();
       for (int i = 0; i < checkInDateSize; i++) {
            String text = checkInDates.get(i).getText();
            if (text.equalsIgnoreCase(date)) {
                checkInDates.get(i).click();
                break;
            }
        }
    }

    public void clickOnCheckOut(){
            wait.until(ExpectedConditions.elementToBeClickable(checkOut));
            checkOut.click();
    }

    public void clickOnAccept() {
        wait.until(ExpectedConditions.elementToBeClickable(checkOut));
        accept.click();

    }

    public String getHotelName() {
        return findMyHotelButton.getText();
    }

    public void clickOnFindMyHotel() {
        wait.until(ExpectedConditions.elementToBeClickable(checkOut));
        findMyHotelButton.click();
    }

}
