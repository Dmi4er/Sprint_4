package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;

    @FindBy(xpath = "//button[@class='App_CookieButton__3cvqF']")
    private WebElement cookieConsentButton;

    @FindBy(xpath = "//button[@class='Button_Button__ra12g']")
    private WebElement topOrderButton;

    @FindBy(xpath = "//button[contains(@class, 'Button_Button') and contains(@class, 'Button_Middle')]")
    private WebElement bottomOrderButton;

    @FindBy(xpath = "//div[contains(@id, 'accordion__heading-')]")
    private List<WebElement> faqQuestionsList;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(cookieConsentButton));
        cookieConsentButton.click();
    }

    public void clickOrderButtonTop() {
        topOrderButton.click();
    }

    public void clickOrderButtonBottom() {
        bottomOrderButton.click();
    }

    public void clickQuestion(int questionIndex) {
        if (questionIndex < 0 || questionIndex >= faqQuestionsList.size()) {
            throw new IndexOutOfBoundsException("Индекс вопроса вне диапазона: " + questionIndex);
        }
        faqQuestionsList.get(questionIndex).click();
    }

    public String getAnswerText(int questionIndex) {
        if (questionIndex < 0 || questionIndex >= faqQuestionsList.size()) {
            throw new IndexOutOfBoundsException("Индекс вопроса вне диапазона: " + questionIndex);
        }

        clickQuestion(questionIndex);

        WebElement answerElement = driver.findElement(By.xpath("//div[@id='accordion__panel-" + questionIndex + "']/p"));

        return getAnswerTextWithWait(answerElement);
    }

    public String getAnswerTextWithWait(WebElement answerElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(answerElement));
        return answerElement.getText();
    }

    // Новый метод для получения элемента вопроса по индексу
    public WebElement getQuestionElement(int questionIndex) {
        if (questionIndex < 0 || questionIndex >= faqQuestionsList.size()) {
            throw new IndexOutOfBoundsException("Индекс вопроса вне диапазона: " + questionIndex);
        }
        return faqQuestionsList.get(questionIndex);
    }
}