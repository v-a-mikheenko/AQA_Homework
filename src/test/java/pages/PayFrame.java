package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PayFrame {
    public WebDriver driver;
    private WebDriverWait wait;

    public PayFrame(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    //Цена в заголовке фрейма
    @FindBy(xpath = "//div[contains(@class, 'pay-description__cost')]//span[1]")
    private WebElement payFrameTitle;

    //Номер в подзаголовке фрейма
    @FindBy(xpath = "//div[contains(@class, 'pay-description__text')]//span")
    private WebElement payFrameSubtitle;

    //Кнопка оплатить в фрейме оплаты
    @FindBy(xpath = "//button[contains(@class, 'colored')]")
    private WebElement bePaidButton;

    //Плейсхолдеры в фрейме оплаты
    @FindBy(xpath = "//label[contains(@class, 'ng-tns-c46-1 ng-star-inserted')]")
    private WebElement cardNumberInput;

    @FindBy(xpath = "//label[contains(@class, 'ng-tns-c46-4 ng-star-inserted')]")
    private WebElement expirationDateInput;

    @FindBy(xpath = "//label[contains(@class, 'ng-tns-c46-5 ng-star-inserted')]")
    private WebElement cvcInput;

    @FindBy(xpath = "//label[contains(@class, 'ng-tns-c46-3 ng-star-inserted')]")
    private WebElement cardHolderNameInput;


    //Иконки платежных систем в Фрейме
    @FindBy(xpath = "//div[@class='icons-container ng-tns-c46-1']//img")
    private List<WebElement> payFramePayLogos;


    // Методы для проверки плейсхолдеров
    public String getCardNumberPlaceholder() {
        return cardNumberInput.getText();
    }

    public String getExpirationDatePlaceholder() {
        return expirationDateInput.getText();
    }

    public String getCvcPlaceholder() {
        return cvcInput.getText();
    }

    public String getCardHolderNamePlaceholder() {
        return cardHolderNameInput.getText();
    }

    // Метод для проверки отображения картинок в Фрейме оплаты
    public boolean checkPayFrameLogoIsDisplayed(String scr) {
        for (WebElement payLogo : payFramePayLogos) {
            if (payLogo.getAttribute("src").contains(scr)) {
                return payLogo.isDisplayed() || payLogo.getAttribute("style").contains("opacity: 0");
            }
        }
        return false;
    }

    public String getTextBePaidButton() {
        return bePaidButton.getText();
    }

    public String getTextPayFrameTitle() {
        return payFrameTitle.getText();
    }

    public String getTextPayFrameSubtitle() {
        return payFrameSubtitle.getText();
    }
}
