package com.BWI.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelSearchPage extends BasePage{

    public HotelSearchPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "p#summary-destination")
    private WebElement destinationsummaryfield;

    @FindBy(css = "p#summary-checkin")
    private WebElement summarycheckinfield;

    @FindBy(css = "p#summary-checkout")
    private WebElement summarycheckoutfield;

    @FindBy(css = ".searchResultsCard.hotelsListItem.selectedCard")
    private WebElement hotelSearchcard;

    @FindBy(id = "btn-modify-stay")
    private WebElement changesearchbutton;

    //btn-modify-stay-update
    @FindBy(id = "btn-modify-stay-update")
    private WebElement updateButton;

    public String destinationSummary() {
        System.out.println(destinationsummaryfield.getText());
        return destinationsummaryfield.getText();

    }

    public WebElement getsummaryCheckin() {
        return summarycheckinfield;
    }
    public WebElement getSummarycheckout() {
        return summarycheckoutfield;
    }

    public WebElement getHotelSearchcard() {
        return hotelSearchcard;
    }

    public WebElement clickOnChangeSearch() {
        return changesearchbutton;
    }
    public void clickOnUpdate() {
        updateButton.click();

    }


}
