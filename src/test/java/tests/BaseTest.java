package tests;

import constants.Constants;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MainPage;

public class BaseTest {
    protected WebDriver driver; // Объект для управления браузером
    protected MainPage mainPage; // Главная страница приложения

    @Before
    public void setUp() {
        driver = new ChromeDriver(); // Инициализация драйвера Chrome
        driver.get(Constants.APPLICATION_URL); // Переход на базовый URL приложения
        mainPage = new MainPage(driver); // Создание объекта главной страницы
    }

    @After
    public void tearDown() {
        driver.quit();
    } // Закрытие браузера после теста
}