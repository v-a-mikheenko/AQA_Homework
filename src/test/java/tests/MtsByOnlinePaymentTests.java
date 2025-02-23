package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MtsByPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MtsByOnlinePaymentTests {

    private static WebDriver driver;
    private static MtsByPage mtsByPage;
    public static final String PHONE_NUMBER = "297777777";
    public static final String SUM = "27.97";


    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://mts.by");

        mtsByPage = new MtsByPage(driver);

        mtsByPage.acceptCookies();
    }


    @Test
    @Order(1)
    public void testTopUpOnlineBlockTitle() {
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        String actualTitle = mtsByPage.getTopUpOnlineBlockTitleText();
        String message = "Неверный заголовок блока";
        assertEquals(expectedTitle, actualTitle, message);
    }


    @Test
    @Order(2)
    public void testEachPayLogoIsDisplayed() {
        mtsByPage.checkEachPayLogoDisplayed();
    }

    @Test
    @Order(8)
    public void testServiceLink() {
        String expectedLink = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String linkHref = mtsByPage.getServiceLinkHrefOnlineReplenishment();
        assertEquals(expectedLink, linkHref);

        mtsByPage.clickServiceLinkOnlineReplenishment();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedLink, currentUrl);
    }


    @Test
    @Order(7)
    public void testOnlineRecharge() {
        mtsByPage.fillFormAndClickContinue(PHONE_NUMBER, SUM);
        assertTrue(mtsByPage.isPayFrameDisplayed(), "Фрейм не открылся после нажатия кнопки «Продолжить»");

        mtsByPage.switchToPayFrame();
        mtsByPage.closePayFrameAndSwitchToDefaultContent();
    }

    @ParameterizedTest
    @Order(3)
    @CsvSource({
            "1, Номер телефона",
            "2, Сумма",
            "3, E-mail для отправки чека"})
    public void testConnectionPlaceholders(int i, String expectedPlaceholder) {
        String actualPlaceholder = "";
        switch (i) {
            case (1):
                actualPlaceholder = mtsByPage.getConnectionPhonePlaceholder();
                break;
            case (2):
                actualPlaceholder = mtsByPage.getConnectionSumPlaceholder();
                break;
            case (3):
                actualPlaceholder = mtsByPage.getConnectionEmailPlaceholder();
                break;
        }
        assertEquals(actualPlaceholder, expectedPlaceholder);
    }

    @ParameterizedTest
    @Order(4)
    @CsvSource({
            "1, Номер абонента",
            "2, Сумма",
            "3, E-mail для отправки чека"})
    public void testInternetPlaceholders(int i, String expectedPlaceholder) {
        String actualPlaceholder = "";
        switch (i) {
            case (1):
                actualPlaceholder = mtsByPage.getInternetPhonePlaceholder();
                break;
            case (2):
                actualPlaceholder = mtsByPage.getInternetSumPlaceholder();
                break;
            case (3):
                actualPlaceholder = mtsByPage.getInternetEmailPlaceholder();
                break;
        }
        assertEquals(actualPlaceholder, expectedPlaceholder);
    }

    @ParameterizedTest
    @Order(5)
    @CsvSource({
            "1, Номер счета на 44",
            "2, Сумма",
            "3, E-mail для отправки чека"})
    public void testInstalmentPlaceholders(int i, String expectedPlaceholder) {
        String actualPlaceholder = "";
        switch (i) {
            case (1):
                actualPlaceholder = mtsByPage.getInstalmentScorePlaceholder();
                break;
            case (2):
                actualPlaceholder = mtsByPage.getInstalmentSumPlaceholder();
                break;
            case (3):
                actualPlaceholder = mtsByPage.getInstalmentEmailPlaceholder();
                break;
        }
        assertEquals(actualPlaceholder, expectedPlaceholder);
    }

    @ParameterizedTest
    @Order(6)
    @CsvSource({
            "1, Номер счета на 2073",
            "2, Сумма",
            "3, E-mail для отправки чека"})
    public void testArrearsPlaceholders(int i, String expectedPlaceholder) {
        String actualPlaceholder = "";
        switch (i) {
            case (1):
                actualPlaceholder = mtsByPage.getArrearsScorePlaceholder();
                break;
            case (2):
                actualPlaceholder = mtsByPage.getArrearsSumPlaceholder();
                break;
            case (3):
                actualPlaceholder = mtsByPage.getArrearsEmailPlaceholder();
                break;
        }
        assertEquals(actualPlaceholder, expectedPlaceholder);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
