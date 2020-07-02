package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FlightConfirmatioPage {

    private WebDriver webDriver;
    private WebDriverWait wait;

    @FindBy(xpath = "//font[contains(text(),'Confirmation')]")
    public WebElement flightConfirmationHeader;

    @FindBy(xpath = "(//font[contains(text(),'USD')])[2]")
    public WebElement price;

    @FindBy(linkText = "SIGN-OFF")
    private WebElement signOffLink;

    public FlightConfirmatioPage(WebDriver driver){
        this.webDriver = driver;
        wait = new WebDriverWait(webDriver, 30);
        PageFactory.initElements(webDriver,this );
    }

    public String getPrice(){
        this.wait.until(ExpectedConditions.visibilityOf(flightConfirmationHeader));
        System.out.println(this.flightConfirmationHeader.getText());
        System.out.println(price.getText());

        String priceValue = price.getText();
        this.signOffLink.click();
        return priceValue;
    }
}
