package tests;

import base.BaseTest;
import com.newtours.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookFlightTest extends BaseTest {

    private String numberOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"numberOfPassengers", "expectedPrice"})
    public void setUpParameters(String numberOfPassengers, String expectedPrice) {
        this.numberOfPassengers = numberOfPassengers;
        this.expectedPrice = expectedPrice;
    }

    @Test
    public void registrationPageTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("selenium", "docker");
        registrationPage.enterUserCreadetials("selenium", "docker");
        registrationPage.submit();
    }


    @Test(dependsOnMethods = "registrationPageTest")
    public void registrationConformationPage() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.goToFlightsFetailsPage();
    }

    @Test(dependsOnMethods = "registrationConformationPage")
    public void flightsDetailsPage() {
        FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
        flightDetailsPage.selectPassengers(numberOfPassengers);
        flightDetailsPage.gotoFindFlightsPage();
    }

    @Test(dependsOnMethods = "flightsDetailsPage")
    public void findFlightPage() {
        FindFlightPage findFlightPage = new FindFlightPage(driver);
        findFlightPage.submitFindFlightPage();
        findFlightPage.gotoFlightConfirmationPage();
    }

    @Test(dependsOnMethods = "findFlightPage")
    public void flightCOnfirmationPage() {
        FlightConfirmatioPage flightConfirmatioPage = new FlightConfirmatioPage(driver);
        String price = flightConfirmatioPage.getPrice();

        Assert.assertEquals(price, expectedPrice);
    }


}
