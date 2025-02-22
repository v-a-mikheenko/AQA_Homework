import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TopUpTests {

    private static WebDriver driver;
    private static HomePage homePage;


    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://mts.by");

        homePage = new HomePage(driver);

        homePage.acceptCookies();
    }


    @Test
    @Order(1)
    public void testTopUpOnlineBlockTitle() {
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        String actualTitle = homePage.getTopUpOnlineBlockTitleText();
        String message = "Неверный заголовок блока";
        assertEquals(expectedTitle, actualTitle, message);
    }


    @Test
    @Order(2)
    public void testEachPayLogoIsDisplayed() {
        homePage.checkEachPayLogoDisplayed();
    }

    @Test
    @Order(4)
    public void testServiceLink() {
        String expectedLink = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String linkHref = homePage.getServiceLinkHrefOnlineReplenishment();
        assertEquals(expectedLink, linkHref);

        homePage.clickServiceLinkOnlineReplenishment();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedLink, currentUrl);
    }


    @Test
    @Order(3)
    public void testOnlineRecharge() {
        homePage.fillFormAndClickContinue("297777777", "10");
        assertTrue(homePage.isPaymentFrameDisplayed(), "Фрейм не открылся после нажатия кнопки «Продолжить»");
        homePage.switchToPayIframeAndClose();
        driver.switchTo().defaultContent();
    }


    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
