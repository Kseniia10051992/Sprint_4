import PO.CustomerInformation;
import PO.HomePageScooter;
import PO.OrderRegistrationWindow;
import PO.RentalInformation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.awt.Color.black;
import static java.awt.Color.gray;
import static org.junit.Assert.assertTrue;
import static сonstants.ColourScooter.BLACK;
import static сonstants.ColourScooter.GREY;
import static сonstants.RentPeriod.*;
import static сonstants.ОrderButton.BOTTOM_BUTTON;
import static сonstants.ОrderButton.TOP_BUTTON;

@RunWith(Parameterized.class)
public class CreateOrderTest extends StartAndFinish {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final int metroNumber;
    private final String phoneNumber;
    private final String date;
    private final String period;
    private final String colour;
    private final String comment;
    private final String textOrder  = "Заказ оформлен";
    private final String button;

    public CreateOrderTest(String button, String name, String surname, String address, int metroNumber, String phoneNumber,
                           String date, String period, String colour, String comment) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroNumber = metroNumber;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.period = period;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParametersTest() {
        return new Object[][]{
                {TOP_BUTTON, "Иван", "Иванов", "улица Мира", 77, "+79500203465", "15.03.2024", ONE_DAY, GREY, "Нет комментариев"},
                {TOP_BUTTON, "Ксения", "Сидорова", "улица Ясная", 123, "+79604567645", "12.03.2024", SEVEN_DAYS, BLACK, "Есть комментарий"},
                {BOTTOM_BUTTON, "Анна", "Иванова", "улица Ленина", 22, "+79305679878", "17.03.2024", FOUR_DAYS, BLACK, "Прошу позвонить заранее"},
                {BOTTOM_BUTTON, "Семен", "Курочкин", "улица Спортивная", 7, "+79656785434", "10.03.2024", TWO_DAYS, GREY, "Комментария нет"},
        };
    }

    @Override
    public void setUp() {
        driver = new FirefoxDriver();
        driver.get( "https://qa-scooter.praktikum-services.ru/" );
        driver.manage().window().maximize();
    }

    @Override
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testButtonOrder() {
        new HomePageScooter(driver)
                .waitForLoadHomePageScooter()
                .clickCreateOrderButton(button);

        new CustomerInformation(driver)
                .waitForLoadOrderPage()
                .fieldName(name)
                .fieldSurname(surname)
                .fieldAddress(address)
                .selectionStateMetro(metroNumber)
                .fieldPhone(phoneNumber)
                .clickNextButton();

        new RentalInformation(driver)
                .waitAboutRentHeader()
                .whenDate(date)
                .rentalPeriod(period)
                .choiceColour(colour)
                .fieldComment(comment)
                .clickFinishOrderButton();

        OrderRegistrationWindow popUpWindow = new OrderRegistrationWindow(driver);
        popUpWindow.clickButtonYes();

        assertTrue(popUpWindow.getTitleText().contains(textOrder));
    }
}