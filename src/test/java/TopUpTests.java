import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
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
        String[] logos = {"visa.svg", "visa-verified.svg", "mastercard.svg", "mastercard-secure.svg", "belkart.svg"};

        for (String src : logos) {
            try {
                assertTrue(homePage.checkPayLogoIsDisplayed(src), "Логотип " + src + " не отображается");
                System.out.println("Логотип " + src + " отображается");
            } catch (NoSuchElementException e) {
                assertTrue(false, "Логотип " + src + " не найден");
            }
        }
    }

    @Test
    public void testServiceLink() {
        String expectedLink = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String expectedTitleText = "Оплата банковской картой";

        String linkHref = homePage.getServiceLinkHrefOnlineReplenishment();
        assertEquals(expectedLink, linkHref, "Ссылка на странице не соответствует ожидаемой");

        homePage.clickServiceLinkOnlineReplenishment();

        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedLink, currentUrl, "Текущий URL не соответствует ожидаемому");

        String actualTitleText = homePage.getServiceTitle();
        assertEquals(expectedTitleText, actualTitleText, "Заголовок на странице не соответствует ожидаемому");
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
