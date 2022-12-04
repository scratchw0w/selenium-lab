package scratchy;

import scratchy.configuration.ConfigurationProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TestPage {
    private HomePage homePage;
    private SearchPage searchPage;
    private ProductPage productPage;

    private CartPage cartPage;

    @BeforeEach
    public void init() {
        System.setProperty("webdriver.chrome.driver", ConfigurationProperties.getProperty("chromedriver"));

        WebDriver driver = new ChromeDriver();
        homePage = new HomePage(driver);

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(ConfigurationProperties.getProperty("homePage"));
    }

    @AfterEach
    public void tearDown() {
        homePage.getDriver().quit();
    }

    @Test
    public void homePageContainsPostForSales_1() {
        String text = ConfigurationProperties.getProperty("salesPost_1");
        List<String> listSalesPost = new ArrayList<>();
        homePage.getNamesSalesPost().forEach(webElement -> listSalesPost.add(webElement.getText()));
        listSalesPost.forEach(System.out::println);
        assertTrue(listSalesPost.contains(text));
    }

    @Test
    public void homePageContainsPostForSales_2() {
        String text = ConfigurationProperties.getProperty("salesPost_2");
        List<String> listSalesPost = new ArrayList<>();
        homePage.getNamesSalesPost().forEach(webElement -> listSalesPost.add(webElement.getText()));
        listSalesPost.forEach(System.out::println);
        assertTrue(listSalesPost.contains(text));
    }

    @Test
    public void searchProduct() {
        String text = ConfigurationProperties.getProperty("searchProduct");
        homePage.enterTextIntoSearchBar(text);
        String actual = homePage.clickSearch().getTextOfResultSearch();
        assertEquals(text, actual);
    }

    @Test
    public void ChangeLanguage() {
        homePage.changeLanguage();
        assertEquals(ConfigurationProperties.getProperty("sell"), homePage.getStringSellOnMainPanel());
    }

    @Test
    public void isSortByPrice() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchProduct"));
        searchPage = homePage.clickSearch();
        assertEquals(ConfigurationProperties.getProperty("sort"), searchPage.isSortOnPrice());
    }


    @Test
    public void isListOfProductsNoEmpty() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchProduct"));
        searchPage =  homePage.clickSearch();
        List<WebElement> products = searchPage.getListOfProduct();
        assertFalse(products.isEmpty());
    }

    @Test
    public void listOfProductsMoreThan10nPage() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchProduct"));
        searchPage = homePage.clickSearch();
        List<WebElement> products = searchPage.getListOfProduct();
        assertTrue(products.size() > 10);
    }

    @Test
    public void isProductWithNameInlist_ExpectedTrue() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchProduct"));
        searchPage = homePage.clickSearch();
        assertTrue(searchPage.isProductByName(ConfigurationProperties.getProperty("productName")));
    }

    @Test
    public void isProductWithNameInlist_ExpectedFalse() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchProduct"));
        searchPage = homePage.clickSearch();
        assertFalse(searchPage.isProductByName("apple"));
    }

    @Test
    public void getNameOfProduct() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchProduct"));
        searchPage = homePage.clickSearch();
        productPage = searchPage.getProductByName(ConfigurationProperties.getProperty("productName"));
        assertEquals(ConfigurationProperties.getProperty("productName"), productPage.getNameProduct());
    }
    @Test
    public void getPriceOfProduct() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchProduct"));
        searchPage = homePage.clickSearch();
        productPage = searchPage.getProductByName(ConfigurationProperties.getProperty("productName"));
        assertEquals(ConfigurationProperties.getProperty("priceOfProduct"), productPage.getPriceOfProduct());
    }


    @Test
    public void getItemModelNumber() {
        homePage.enterTextIntoSearchBar(ConfigurationProperties.getProperty("searchProduct"));
        searchPage = homePage.clickSearch();
        productPage = searchPage.getProductByName(ConfigurationProperties.getProperty("productName"));
        assertEquals(ConfigurationProperties.getProperty("itemModelNumber"), productPage.getItemModelNumber());
    }

    @Test
    void getStatusCart() {
        cartPage = homePage.getCartPage();
        assertEquals(ConfigurationProperties.getProperty("statusCart"), cartPage.getStatusCart());
    }

    @Test
    void getLocatorSigIn() {
        cartPage = homePage.getCartPage();
        assertEquals(ConfigurationProperties.getProperty("signIn"), cartPage.getLocatorSigIn());
    }

    @Test
    void getLocatorSignUp() {
        cartPage = homePage.getCartPage();
        assertEquals(ConfigurationProperties.getProperty("signUp"), cartPage.getLocatorSignUp());
    }
}
