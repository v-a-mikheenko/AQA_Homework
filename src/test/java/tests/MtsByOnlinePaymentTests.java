package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MtsByPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void testTopUpOnlineBlockTitle() {
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        String actualTitle = mtsByPage.getTopUpOnlineBlockTitleText();
        assertEquals(expectedTitle, actualTitle, "Неверный заголовок блока");
    }

    @ParameterizedTest
    @DisplayName("Проверка отображения логотипов платёжных систем в блоке 'Онлайн пополнение без комиссии'")
    @ValueSource(strings = {"visa.svg", "visa-verified.svg", "mastercard.svg", "mastercard-secure.svg", "belkart.svg"})
    public void testEachPayLogoIsDisplayed(String src) {
        assertTrue(mtsByPage.checkPayLogoIsDisplayed(src), "Логотип " + src + " не отображается");
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testServiceLink() {
        String expectedLink = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String expectedTitleText = "Оплата банковской картой";

        String linkHref = mtsByPage.getServiceLinkHrefOnlineReplenishment();
        assertEquals(expectedLink, linkHref, "Ссылка на странице не соответствует ожидаемой");

        mtsByPage.clickServiceLinkOnlineReplenishment();

        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedLink, currentUrl, "Текущий URL не соответствует ожидаемому");

        String actualTitleText = mtsByPage.getServiceTitle();
        assertEquals(expectedTitleText, actualTitleText, "Заголовок на странице не соответствует ожидаемому");

        driver.navigate().back();
    }


    @Test
    @DisplayName("Проверка работы кнопки 'Продолжить' в блоке 'Онлайн пополнение без комиссии'")
    public void testOnlineRecharge() {
        mtsByPage.fillFormAndClickContinue(PHONE_NUMBER, SUM);
        assertTrue(mtsByPage.isPayFrameDisplayed(), "Фрейм не открылся после нажатия кнопки «Продолжить»");

        mtsByPage.switchToPayFrame();
        mtsByPage.closePayFrameAndSwitchToDefaultContent();
    }

    @ParameterizedTest
    @DisplayName("Проверка плейсхолдеров полей варианта услуги-'Услуги связи'")
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
    @DisplayName("Проверка плейсхолдеров полей варианта услуги-'Домашний интернет'")
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
    @DisplayName("Проверка плейсхолдеров полей варианта услуги-'Рассрочка'")
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
    @DisplayName("Проверка плейсхолдеров полей варианта услуги-'Задолженность'")
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
