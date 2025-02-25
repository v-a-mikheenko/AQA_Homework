import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    //Принять куки
    @FindBy(xpath = "//div[contains(@class, 'cookie')]")
    private WebElement cookiesBanner;

    @FindBy(xpath = "//*[contains(@id, 'cookie-agree')]")
    private WebElement acceptCookiesButton;

    //Задание 1
    @FindBy(xpath = "//div[contains(@class, 'pay__wrapper')]//h2")
    private WebElement topUpOnlineBlockTitle;

    //Задание 2
    @FindBy(xpath = "//section[@class='pay']//img")
    private List<WebElement> payLogos;

    //Задание 3
    @FindBy(xpath = "//a[text()='Подробнее о сервисе']")
    private WebElement serviceLink;

    @FindBy(xpath = "//*[contains(text(), 'Оплата банковской картой')]")
    private WebElement serviceTitle;

    //Задание 4
    @FindBy(css = "input[placeholder='Номер телефона']")
    private WebElement phoneNumberInput;

    @FindBy(css = "input[placeholder='Сумма']")
    private WebElement amountInput;

    @FindBy(xpath = "//button[contains(text(), 'Продолжить')]")
    private WebElement continueButton;

    @FindBy(css = "iframe.bepaid-iframe")
    private WebElement payFrame;


    //Принять куки
    public void acceptCookies() {
        if (cookiesBanner.isDisplayed()) {
            acceptCookiesButton.click();
        }
    }

    //Задание 1
    public String getTopUpOnlineBlockTitleText() {
        return topUpOnlineBlockTitle.getText();
    }

    //Задание 2
    public boolean checkPayLogoIsDisplayed(String scr) {
        for (WebElement payLogo : payLogos) {
            if (payLogo.getAttribute("src").contains(scr)) {
                return payLogo.isDisplayed();
            }
        }
        return false;
    }

    //Задание 3
    public void clickServiceLinkOnlineReplenishment() {
        wait.until(ExpectedConditions.elementToBeClickable(serviceLink)).click();
    }

    public String getServiceLinkHrefOnlineReplenishment() {
        return wait.until(ExpectedConditions.visibilityOf(serviceLink)).getAttribute("href");
    }

    public String getServiceTitle() {
        return serviceTitle.getText();
    }


    //Задание 4
    public void fillFormAndClickContinue(String phoneNumber, String amount) {
        phoneNumberInput.sendKeys(phoneNumber);
        amountInput.sendKeys(amount);
        continueButton.click();
    }

    public boolean isPaymentFrameDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(payFrame));
            return payFrame.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}