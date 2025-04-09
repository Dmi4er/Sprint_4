package pageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class MainPage {
    private WebDriver driver;

    // Кнопка для принятия файлов cookie
    @FindBy(xpath = "//button[@class='App_CookieButton__3cvqF']")
    private WebElement cookieConsentButton;

    // Кнопка «Заказать» в верхней части страницы
    @FindBy(xpath = "//button[@class='Button_Button__ra12g']")
    private WebElement topOrderButton;

    // Кнопка «Заказать» в нижней части страницы
    @FindBy(xpath = "//button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private WebElement bottomOrderButton;

    // Конструктор для инициализации элементов страницы
    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Метод для принятия файлов cookie
    public void acceptCookies() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(cookieConsentButton)); // Ждем, пока кнопка станет кликабельной
        cookieConsentButton.click(); // Нажал кнопку принятия cookie
    }

    // Методы для нажатия на кнопки заказа
    public void clickOrderButtonTop() {
        topOrderButton.click();
    }

    public void clickOrderButtonBottom() {
        bottomOrderButton.click();
    }

    // Локаторы для вопросов FAQ
    @FindBy(xpath = "//div[@id='accordion__heading-0']")
    private WebElement question1;

    @FindBy(xpath = "//div[@id='accordion__heading-1']")
    private WebElement question2;

    @FindBy(xpath = "//div[@id='accordion__heading-2']")
    private WebElement question3;

    @FindBy(xpath = "//div[@id='accordion__heading-3']")
    private WebElement question4;

    @FindBy(xpath = "//div[@id='accordion__heading-4']")
    private WebElement question5;

    @FindBy(xpath = "//div[@id='accordion__heading-5']")
    private WebElement question6;

    @FindBy(xpath = "//div[@id='accordion__heading-6']")
    private WebElement question7;

    @FindBy(xpath = "//div[@id='accordion__heading-7']")
    private WebElement question8;

    // Локаторы для ответов на вопросы FAQ
    @FindBy(xpath = "//div[@id='accordion__panel-0']/p")
    private WebElement answer1;

    @FindBy(xpath = "//div[@id='accordion__panel-1']/p")
    private WebElement answer2;

    @FindBy(xpath = "//div[@id='accordion__panel-2']/p")
    private WebElement answer3;

    @FindBy(xpath = "//div[@id='accordion__panel-3']/p")
    private WebElement answer4;

    @FindBy(xpath = "//div[@id='accordion__panel-4']/p")
    private WebElement answer5;

    @FindBy(xpath = "//div[@id='accordion__panel-5']/p")
    private WebElement answer6;

    @FindBy(xpath = "//div[@id='accordion__panel-6']/p")
    private WebElement answer7;

    @FindBy(xpath = "//div[@id='accordion__panel-7']/p")
    private WebElement answer8;

    // Список всех вопросов FAQ
    @FindBy(xpath = "//div[contains(@id, 'accordion__heading-')]")
    private List<WebElement> faqQuestionsList;

    // Метод для получения элемента вопроса по индексу
    public WebElement getQuestionElement(int index) {
        return faqQuestionsList.get(index);
    }

    // Методы для нажатия на вопросы FAQ
    public void clickQuestion1() {
        question1.click();
    }

    public void clickQuestion2() {
        question2.click();
    }

    public void clickQuestion3() {
        question3.click();
    }

    public void clickQuestion4() {
        question4.click();
    }

    public void clickQuestion5() {
        question5.click();
    }

    public void clickQuestion6() {
        question6.click();
    }

    public void clickQuestion7() {
        question7.click();
    }

    public void clickQuestion8() {
        question8.click();
    }

    // Метод для получения текста ответа с ожиданием его видимости
    public String getAnswerTextWithWait(WebElement answerElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(answerElement));
        return answerElement.getText();
    }

    // Методы для получения текста ответов на вопросы FAQ
    public String getAnswer1Text() {
        return getAnswerTextWithWait(answer1);
    }

    public String getAnswer2Text() {
        return getAnswerTextWithWait(answer2);
    }

    public String getAnswer3Text() {
        return getAnswerTextWithWait(answer3);
    }

    public String getAnswer4Text() {
        return getAnswerTextWithWait(answer4);
    }

    public String getAnswer5Text() {
        return getAnswerTextWithWait(answer5);
    }

    public String getAnswer6Text() {
        return getAnswerTextWithWait(answer6);
    }

    public String getAnswer7Text() {
        return getAnswerTextWithWait(answer7);
    }

    public String getAnswer8Text() {
        return getAnswerTextWithWait(answer8);
    }
}