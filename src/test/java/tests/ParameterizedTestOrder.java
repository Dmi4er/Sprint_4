package tests;

import constants.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MainPage;
import pageobjects.OrderPage;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ParameterizedTestOrder extends BaseTest { // Наследование от BaseTest

    private OrderPage orderPage; // Страница заказа

    // Конструктор для параметров теста
    private String firstName; // Имя
    private String lastName; // Фамилия
    private String deliveryAddress; // Адрес доставки
    private String contactNumber; // Номер телефона
    private String deliveryDate; // Дата доставки
    private String rentalDuration;// Срок аренды
    private String scooterColor; // Цвет самоката
    private String additionalNote; // Дополнительная заметка
    private boolean isTopButtonUsed; // Использовать верхнюю кнопку

    // Конструктор для параметров
    public ParameterizedTestOrder(boolean isTopButtonUsed, String name, String surname, String address, String phoneNumber, String deliveryDay, String rentalPeriod, String color, String note) {
        this.isTopButtonUsed = isTopButtonUsed;
        this.firstName = name;
        this.lastName = surname;
        this.deliveryAddress = address;
        this.contactNumber = phoneNumber;
        this.deliveryDate = deliveryDay;
        this.rentalDuration = rentalPeriod;
        this.scooterColor = color;
        this.additionalNote = note;
    }

    // Метод для предоставления данных для теста
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {
                        true,
                        "Алекс",
                        "Смирнов",
                        "Санкт-Петербург",
                        "+79991234567",
                        "25",
                        "3",
                        "red",
                        "Пожалуйста, будьте осторожны!"
                },
                {
                        false,
                        "Иван",
                        "Петров",
                        "Екатеринбург",
                        "+78887776655",
                        "30",
                        "5",
                        "blue",
                        "Нужен самокат на выходные!"
                }
        });
    }

    @Override
    @Before
    public void setUp() {
        super.setUp(); // Вызов метода setUp() из BaseTest
        mainPage.acceptCookies(); // Принять куки на главной странице
    }

    @Test
    public void testOrderFlow() {
        // Нажимаю на кнопку "Заказать" (вверху или внизу)
        if (isTopButtonUsed) {
            mainPage.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonBottom();
        }

        // Переход на страницу оформления заказа
        orderPage = new OrderPage(driver);

        // Заполнение полей первого шага оформления заказа
        fillFirstStep();

        // Нажимаю кнопку "Далее"
        orderPage.clickNextButton();

        // Заполнение полей второго шага оформления заказа
        fillSecondStep();

        // Нажимаю кнопку "Заказать"
        orderPage.clickOrderButton();

        // Подтверждение заказа
        orderPage.confirmOrder();

        // Проверка успешного создания заказа
        assertOrderConfirmation();
    }

    private void fillFirstStep() {
        orderPage.enterName(this.firstName);
        orderPage.enterSurname(this.lastName);
        orderPage.enterAddress(this.deliveryAddress);
        orderPage.selectFirstMetroStation();
        orderPage.enterPhone(this.contactNumber);
    }

    private void fillSecondStep() {
        if (this.deliveryDate.equals("20")) {
            orderPage.selectDeliveryDate20();
        } else if (this.deliveryDate.equals("14")) {
            orderPage.selectDeliveryDate14();
        }

        if (this.rentalDuration.equals("1")) {
            orderPage.selectFirstRentalPeriod();
        } else if (this.rentalDuration.equals("2")) {
            orderPage.selectSecondRentalPeriod();
        }

        if (this.scooterColor.equals("black")) {
            orderPage.selectBlackColor();
        } else if (this.scooterColor.equals("grey")) {
            orderPage.selectGreyColor();
        }

        orderPage.enterComment(this.additionalNote);
    }

    private void assertOrderConfirmation() {
        assertTrue("Всплывающее окно с сообщением об успешном создании заказа не отображается",
                orderPage.isOrderConfirmationHeaderDisplayed());
    }
}