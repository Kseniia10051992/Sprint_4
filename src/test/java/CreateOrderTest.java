import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import po.CustomerInformation;
import po.HomePageScooter;
import po.OrderRegistrationWindow;
import po.RentalInformation;

import static java.awt.Color.black;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.Colors.BLACK;
import static org.openqa.selenium.support.Colors.GREY;
import static сonstants.RentalPeriod.*;
import static сonstants.ОrderButton.BOTTOM_BUTTON;
import static сonstants.ОrderButton.TOP_BUTTON;


@RunWith(Parameterized.class)
public class CreateOrderTest extends StartAndFinish {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final int stateMetro;
    private final String phoneNumber;
    private final String date;
    private final String period;
    private final String colour;
    private final String comment;
    private final String textOrder = "Заказ оформлен";
    private final String button;

    public CreateOrderTest(String button, String name, String surname, String address, int stateMetro, String phoneNumber,
                           String date, String period, String colour, String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stateMetro = stateMetro;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.period = period;
        this.colour = colour;
        this.comment = comment;

    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {TOP_BUTTON, "Ксения", "Иванова", "улица Спортивная", 77, "+79604563452", "14.03.2024", ONE_DAY, "BLACK", "Нет комментариев"},
                {TOP_BUTTON, "Галина", "Федорова", "улица Лесная", 12, "+79305678909", "10.03.2024", FIVE_DAYS, "GREY", "Просьба позвонить"},
                {BOTTOM_BUTTON, "Олег", "Сидоров", "улица Ленина", 22, "+79605674356", "20.03.2024", TWO_DAYS, "BLACK", "Есть Комментарий"},



        };
    }

    @Override
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().window().maximize();
    }

    @Override
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void testClickOrderButton() {
        new HomePageScooter(driver)
                .waitForLoadHomePageScooter()
                .clickCreateOrderButton(button);

        new CustomerInformation(driver)
                .waitForLoadOrderPage()
                .fieldName(name)
                .fieldSurname(surname)
                .fieldAddress(address)
                .selectionStateMetro(stateMetro)
                .fieldPhone(phoneNumber)
                .clickNextButton();
        new RentalInformation(driver)
                .waitAboutRentHeader()
                .whenDate(date)
                .rentalPeriod(period)
                .choiceColour(colour)
                .fieldComment(comment)
                .clickFinishOrderButton();

        OrderRegistrationWindow orderRegistrationWindow = new OrderRegistrationWindow(driver);
        orderRegistrationWindow.clickButtonYes();

        assertTrue(orderRegistrationWindow.getTitleText().contains(textOrder));


    }
}