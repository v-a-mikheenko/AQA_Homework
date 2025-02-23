package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MtsByPage;
import pages.PayFrame;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayFrameTests {

    private static WebDriver driver;
    private static MtsByPage mtsByPage;
    public static PayFrame payFrame;
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
        mtsByPage.fillFormAndClickContinue(PHONE_NUMBER, SUM);
        mtsByPage.switchToPayFrame();

        payFrame = new PayFrame(driver);
    }

    @ParameterizedTest
    @CsvSource({
            "1, Номер карты",
            "2, Срок действия",
            "3, CVC",
            "4, Имя держателя (как на карте)"})
    public void testPlaceholdersPayFrame(int i, String expectedPlaceholder) {
        String actualPlaceholder = "";
        switch (i) {
            case (1):
                actualPlaceholder = payFrame.getCardNumberPlaceholder();
                break;
            case (2):
                actualPlaceholder = payFrame.getExpirationDatePlaceholder();
                break;
            case (3):
                actualPlaceholder = payFrame.getCvcPlaceholder();
                break;
            case (4):
                actualPlaceholder = payFrame.getCardHolderNamePlaceholder();
                break;
        }
        assertEquals(actualPlaceholder, expectedPlaceholder);
    }

    @Test
    public void testPayFrameEachLogoIsDisplayed() {
        payFrame.arePaymentIconsDisplayed();
    }

    @Test
    public void testPayFrameSumEqualsBePaidButton() {
        assertEquals("Оплатить " + SUM + " BYN", payFrame.getTextBePaidButton());
    }

    @Test
    public void testPayFrameSumTextEqualsTitle() {
        assertEquals(SUM + " BYN", payFrame.getTextPayFrameTitle());
    }

    @Test
    public void testPayFramePhoneNumberTextEqualsSubtitle() {
        assertEquals("Оплата: Услуги связи Номер:375" + PHONE_NUMBER, payFrame.getTextPayFrameSubtitle());
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
