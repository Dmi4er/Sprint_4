package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FaqTest extends BaseTest {

    private int questionIndex; // Индекс вопроса
    private String expectedAnswer; // Ожидаемый ответ

    public FaqTest(int questionNumber, String expectedAnswer) {
        this.questionIndex = questionNumber; // Индекс вопросов начинается с 1
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {8, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Test
    public void validateFaqResponses() {
        WebElement faqSection = mainPage.getQuestionElement(questionIndex - 1); // Индексируем с нуля
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", faqSection);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(faqSection));

        mainPage.clickQuestion(questionIndex - 1); // Метод для клика по вопросу (индексируем с нуля)

        String actualAnswer = mainPage.getAnswerText(questionIndex - 1); // Метод для получения текста ответа (индексируем с нуля)

        assertEquals("Текст ответа на вопрос " + questionIndex + " не совпадает", expectedAnswer, actualAnswer);
    }
}