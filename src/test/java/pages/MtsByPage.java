package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsByPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public MtsByPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    //Принять куки
    @FindBy(xpath = "//div[contains(@class, 'cookie')]")
    private WebElement cookiesBanner;

    @FindBy(xpath = "//*[contains(@id, 'cookie-agree')]")
    private WebElement acceptCookiesButton;

    //Заголовок "Онлайн пополнение без комиссии"
    @FindBy(xpath = "//div[contains(@class, 'pay__wrapper')]//h2")
    private WebElement topUpOnlineBlockTitle;

    //Логотипы платежных систем "Услуги связи"
    @FindBy(xpath = "//section[@class='pay']//img")
    private List<WebElement> payLogos;

    //Ссылка "Подробнее о сервисе"
    @FindBy(xpath = "//a[text()='Подробнее о сервисе']")
    private WebElement serviceLink;

    //Заголовок "Подробнее о сервисе"
    @FindBy(xpath = "//*[contains(text(), 'Оплата банковской картой')]")
    private WebElement serviceTitle;

    //Задание 4
    @FindBy(css = "input[placeholder='Номер телефона']")
    private WebElement phoneNumberInput;

    @FindBy(css = "input[placeholder='Сумма']")
    private WebElement amountInput;

    //Кнопка продолжить
    @FindBy(xpath = "//button[contains(text(), 'Продолжить')]")
    private WebElement continueButton;

    //Фрейм оплаты
    @FindBy(xpath = "//iframe[contains(@class, 'bepaid-iframe')]")
    private WebElement payFrame;

    //Кнопка закрытия фрейма оплаты
    @FindBy(xpath = "//svg-icon[@class='header__close-icon']")
    private WebElement closeFrameButton;

    //Услуги связи
    @FindBy(xpath = "//*[contains(@id, 'connection-phone')]")
    private WebElement connectionPhone;

    @FindBy(xpath = "//*[contains(@id, 'connection-sum')]")
    private WebElement connectionSum;

    @FindBy(xpath = "//*[contains(@id, 'connection-email')]")
    private WebElement connectionEmail;

    //Домашний интернет
    @FindBy(xpath = "//*[contains(@id, 'internet-phone')]")
    private WebElement internetPhone;

    @FindBy(xpath = "//*[contains(@id, 'internet-sum')]")
    private WebElement internetSum;

    @FindBy(xpath = "//*[contains(@id, 'internet-email')]")
    private WebElement internetEmail;

    //Рассрочка
    @FindBy(xpath = "//*[contains(@id, 'score-instalment')]")
    private WebElement instalmentScore;

    @FindBy(xpath = "//*[contains(@id, 'instalment-sum')]")
    private WebElement instalmentSum;

    @FindBy(xpath = "//*[contains(@id, 'instalment-email')]")
    private WebElement instalmentEmail;

    //Задолженность
    @FindBy(xpath = "//*[contains(@id, 'score-arrears')]")
    private WebElement arrearsScore;

    @FindBy(xpath = "//*[contains(@id, 'arrears-sum')]")
    private WebElement arrearsSum;

    @FindBy(xpath = "//*[contains(@id, 'arrears-email')]")
    private WebElement arrearsEmail;


    //Принять куки
    public void acceptCookies() {
        if (cookiesBanner.isDisplayed()) {
            acceptCookiesButton.click();
        }
    }

    //Проверка теста заголовка "Онлайн пополнение без комиссии"
    public String getTopUpOnlineBlockTitleText() {
        return topUpOnlineBlockTitle.getText();
    }

    //Проверка отображения логотипов платежных систем "Услуги связи"
    public boolean checkPayLogoIsDisplayed(String scr) {
        for (WebElement payLogo : payLogos) {
            if (payLogo.getAttribute("src").contains(scr)) {
                return payLogo.isDisplayed();
            }
        }
        return false;
    }

    //Проверка работы ссылки "Подробнее о сервисе"
    public void clickServiceLinkOnlineReplenishment() {
        wait.until(ExpectedConditions.elementToBeClickable(serviceLink)).click();
    }

    public String getServiceLinkHrefOnlineReplenishment() {
        return wait.until(ExpectedConditions.visibilityOf(serviceLink)).getAttribute("href");
    }

    public String getServiceTitle() {
        return wait.until(ExpectedConditions.visibilityOf(serviceTitle)).getText();
    }

    //Заполнение полей: "Номер телефона", "Сумма" и нажатие кнопки "Продолжить"
    public void fillFormAndClickContinue(String phoneNumber, String amount) {
        phoneNumberInput.sendKeys(phoneNumber);
        amountInput.sendKeys(amount);
        continueButton.click();
    }

    public boolean isPayFrameDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(payFrame));
            return payFrame.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void switchToPayFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(payFrame));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[formcontrolname=creditCard] + label")));
    }

    public void closePayFrameAndSwitchToDefaultContent() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//svg-icon[@class='header__close-icon']")));
        wait.until(ExpectedConditions.elementToBeClickable(closeFrameButton)).click();
        driver.switchTo().defaultContent();
    }

    //Получение текста плейсхолдеров Услуги связи
    public String getConnectionPhonePlaceholder() {
        return connectionPhone.getAttribute("placeholder");
    }

    public String getConnectionSumPlaceholder() {
        return connectionSum.getAttribute("placeholder");
    }

    public String getConnectionEmailPlaceholder() {
        return connectionEmail.getAttribute("placeholder");
    }

    //Получение текста плейсхолдеров Домашний интернет
    public String getInternetPhonePlaceholder() {
        return internetPhone.getAttribute("placeholder");
    }

    public String getInternetSumPlaceholder() {
        return internetSum.getAttribute("placeholder");
    }

    public String getInternetEmailPlaceholder() {
        return internetEmail.getAttribute("placeholder");
    }

    //Получение текста плейсхолдеров Рассрочка
    public String getInstalmentScorePlaceholder() {
        return instalmentScore.getAttribute("placeholder");
    }

    public String getInstalmentSumPlaceholder() {
        return instalmentSum.getAttribute("placeholder");
    }

    public String getInstalmentEmailPlaceholder() {
        return instalmentEmail.getAttribute("placeholder");
    }

    //Получение текста плейсхолдеров Задолженность
    public String getArrearsScorePlaceholder() {
        return arrearsScore.getAttribute("placeholder");
    }

    public String getArrearsSumPlaceholder() {
        return arrearsSum.getAttribute("placeholder");
    }

    public String getArrearsEmailPlaceholder() {
        return arrearsEmail.getAttribute("placeholder");
    }
}
