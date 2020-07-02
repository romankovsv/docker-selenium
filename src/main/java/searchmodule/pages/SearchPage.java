package searchmodule.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage {

    private WebDriver driver;
    private WebDriverWait wait;


    @FindBy(name = "q")
    private WebElement serachField;

    @FindBy(id = "search_button_homepage")
    private WebElement serachBtn;

    @FindBy(linkText = "Videos")
    private WebElement videosLink;

    @FindBy(className = "tile--vid")
    private List<WebElement> videos;


    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public void goTo() {
        this.driver.get("https://duckduckgo.com/");
    }

    public void doSearch(String keyword) {
        this.wait.until(ExpectedConditions.visibilityOf(serachField));
        this.serachField.sendKeys(keyword);
        this.serachBtn.click();
    }

    public void goToVideos() {
        this.wait.until(ExpectedConditions.visibilityOf(videosLink));
        videosLink.click();
    }

    public int returnNumberOfVideos() {
        this.wait.until(ExpectedConditions.visibilityOfAllElements(videos));
        System.out.println("Size: " + videos.size());
        return videos.size();
    }
}

