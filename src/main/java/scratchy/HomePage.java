package scratchy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static java.lang.Thread.sleep;

public class HomePage {
    private final WebDriver driver;

    By searchLocator = By.id("twotabsearchtextbox");
    By searchButtonLocator = By.id("nav-search-submit-button");
    By nameSalesPost = By.className("a-cardui-header");

    By languageLocator = By.xpath("//*[@id=\"icp-nav-flyout\"]");
    By languageES = By.xpath("//*[@id=\"nav-flyout-icp\"]/div[2]/a[1]");

    By hrefSellOnMainPanel = By.xpath("//*[@id=\"nav-xshop\"]/a[5]");

    By cartLocator = By.xpath("//a[@id='nav-cart']");

    public HomePage(WebDriver driver) { this.driver = driver;}
    public WebDriver getDriver() {return driver;}

    public void enterTextIntoSearchBar(String text) {
        driver.findElement(searchLocator).sendKeys(text);
    }
    public SearchPage clickSearch() {
        driver.findElement(searchButtonLocator).click();
        return new SearchPage(driver);
    }
    public void changeLanguage() {
        Actions builder = new Actions(driver);
        WebElement webelement = driver.findElement(languageLocator);
        builder.moveToElement(webelement).perform();
        driver.findElement(languageES).click();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String getStringSellOnMainPanel() {
        return driver.findElement(hrefSellOnMainPanel).getText();
    }

    public List<WebElement> getNamesSalesPost() {
        return driver.findElements(nameSalesPost);
    }

    public CartPage getCartPage() {
        driver.findElement(cartLocator).click();
        return new CartPage(driver);
    }
}
