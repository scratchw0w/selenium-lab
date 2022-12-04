package scratchy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    private final WebDriver driver;

    @FindBy(id = "productTitle")
    WebElement nameProduct;

    @FindBy(xpath = "//*[@id=\"availability\"]/span")
    WebElement priceOfProduct;

    @FindBy(xpath = "//*[@id=\"detailBullets_feature_div\"]/ul/li[3]/span/span[2]")
    WebElement itemModelNumber;

    public ProductPage(WebDriver driver) {this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getNameProduct() {return nameProduct.getText();}

    public String getPriceOfProduct() {return  priceOfProduct.getText();}

    public String getItemModelNumber() {return  itemModelNumber.getText();}
}
