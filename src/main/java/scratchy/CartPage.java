package scratchy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private final WebDriver driver;

    @FindBy(xpath = "//div[@class='a-row sc-your-amazon-cart-is-empty']/h2")
    WebElement statusCart;

    @FindBy(xpath = "//a[@id='a-autoid-0-announce']")
    WebElement locatorSigIn;

    @FindBy(xpath = "//a[@id='a-autoid-1-announce']/span[@class='a-size-base-plus']")
    WebElement locatorSignUp;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getStatusCart() { return statusCart.getText().trim();}

    public String getLocatorSigIn() {
        return locatorSigIn.getText().trim();
    }

    public String getLocatorSignUp() {
        return locatorSignUp.getText().trim();
    }
}
