package com.BWI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BasePage {

    private WebElement Autosuggestive;


    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "destination-input")
    private WebElement destinationInputTextBox;

    @FindBy(css = "input#checkin")
    private WebElement checkin;

    @FindBy(css = "td[data-handler='selectDay']")
    private List<WebElement> checkindates;

    @FindBy(css = ".ui-datepicker-month")
    private WebElement checkinmonth;

    @FindBy(css = ".ui-datepicker-next.ui-corner-all")
    private WebElement next;

    @FindBy(css = "input#checkout")
    private WebElement checkout;

    @FindBy(css = "td[data-handler='selectDay']")
    private List<WebElement> checkoutdates;

    @FindBy(css = ".ui-datepicker-month")
    private WebElement checkoutmonth;

    @FindBy(css = ".ui-datepicker-next.ui-corner-all")
    private WebElement checkoutnext;

    //onetrust-accept-btn-handler
    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement accept;

    @FindBy(id = "btn-modify-stay-update")
    private WebElement findmyhotelbutton;

    public String homePageTitle(){
       return driver.getTitle();
    }

    public String enterDestinationInput(String destination) {
        destinationInputTextBox.clear();
        destinationInputTextBox.sendKeys(destination);

        try{
            WebElement autosuggestivedestination=driver.findElement(By.xpath("//ul[@id='google-suggestions']//li[@data-place='" + destination + "']"));

            autosuggestivedestination.click();

        }catch(StaleElementReferenceException e) {
            WebElement autosuggestivedestination=driver.findElement(By.xpath("//ul[@id='google-suggestions']//li[@data-place='" + destination + "']"));
            autosuggestivedestination.click();
        }
        return destinationInputTextBox.getText();


    }
    
    public WebElement ClickOnCheckIn() {
        checkin.click();
        return checkin;
    }

    public void selectMonth(String month)  {
       while(!checkinmonth.getText().contains(month)) {
           try {
               Thread.sleep(2000);
               next.click();
           }catch(StaleElementReferenceException | InterruptedException e){
               next.click();
           }
       }
    }
    public void selectdate(String date) {
        //Grab common attribute//Put into list and iterate
  /*      try{
            for(WebElement day: checkindates) {
                if (day.getText().equals(date)) {
                    day.click();
                }
            }
        }catch(StaleElementReferenceException e){
        for(WebElement day: checkindates){
           if( day.getText().equals(date)) {
               day.click();
           }
           }*/


       int checkindatesize = checkindates.size();
       for (int i = 0; i < checkindatesize; i++) {
            String text = checkindates.get(i).getText();
            if (text.equalsIgnoreCase(date)) {
                checkindates.get(i).click();
                break;
            }
        }
    }

    //checkout
    public WebElement clickOnCheckout(){
        try{

            checkout.click();
        }
        catch(StaleElementReferenceException e){
            checkout.click();
        }
        return checkout;

    }
    public void clickOnAccept() {
        accept.click();

    }

    public WebElement findmyhotel() {
       return findmyhotelbutton;
    }


}
