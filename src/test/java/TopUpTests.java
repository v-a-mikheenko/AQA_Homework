import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TopUpTests {

    private WebDriver driver;
    private HomePage homePage;


    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mts.by");

        homePage = new HomePage(driver);

        homePage.acceptCookies();
    }


    @Test
    public void testTopUpOnlineBlockTitle() {
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        String actualTitle = homePage.getTopUpOnlineBlockTitleText();
        String message = "Неверный заголовок блока";
        assertEquals(expectedTitle, actualTitle, message);
    }


    @Test
    public void testEachPayLogoIsDisplayed() {
        homePage.checkEachPayLogoDisplayed();
    }

    @Test
    public void testServiceLink() {
        String expectedLink = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String linkHref = homePage.getServiceLinkHrefOnlineReplenishment();
        assertEquals(expectedLink, linkHref);

        homePage.clickServiceLinkOnlineReplenishment();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedLink, currentUrl);
    }


    @Test
    public void testOnlineRecharge() {
        homePage.fillFormAndClickContinue("297777777", "10");
        assertTrue(homePage.isPaymentFrameDisplayed(), "Фрейм не открылся после нажатия кнопки «Продолжить»");
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
