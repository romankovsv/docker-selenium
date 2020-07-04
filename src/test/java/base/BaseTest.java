package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setUpDriver(ITestContext context) throws MalformedURLException {

//        String host = "localhost";
//        DesiredCapabilities dc;
//
//        if(System.getProperty("BROWSER") != null &&
//                   System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
//            dc = DesiredCapabilities.firefox();
//        }else {
//            dc = DesiredCapabilities.chrome();
//        }
//
//        if(System.getProperty("HUB_HOST") != null){
//            host = System.getProperty("HUB_HOST");
//        }
//
//        String completeURL = "http://" + host +":4444/wd/hub";
//        dc.setCapability("name", context.getCurrentXmlTest().getName());
//        this.driver = new RemoteWebDriver(new URL(completeURL), dc);


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        driver = new RemoteWebDriver(
                URI.create("http://localhost:4444/wd/hub").toURL(),
                capabilities
        );
    }

    @AfterTest
    public  void tearDown(){
        this.driver.quit();
    }
}
