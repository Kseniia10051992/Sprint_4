import PO.CustomerInformation;
import PO.HomePageScooter;
import PO.OrderRegistrationWindow;
import PO.RentalInformation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;


public class CreateOrderTest {
    WebDriver driver;
    public static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get( PAGE_URL );
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testTopButtonOrder() {
        HomePageScooter homePageScooter = new HomePageScooter( driver );
        CustomerInformation customerInformation = new CustomerInformation( driver );
        RentalInformation rentalInformation = new RentalInformation( driver );

        homePageScooter.waitForLoadHomePageScooter()
                .clickTopOrderButton();

        customerInformation.waitForLoadOrderPage()
                .fieldName( "Анна" )
                .fieldSurname( "Иванова" )
                .fieldAddress( "улица Спортивная" )
                .selectionStateMetro( 22 )
                .fieldPhone( "+79504321234" )
                .clickNextButton();
        rentalInformation.waitAboutRentHeader()
                .whenDate( "15.03.2024" )
                .rentalPeriod( "сутки" )
                .fieldComment( "Нет комментариев")
                .clickFinishOrderButton();

        OrderRegistrationWindow orderRegistrationWindow = new OrderRegistrationWindow( driver );
        orderRegistrationWindow.clickButtonYes();
        assertTrue(orderRegistrationWindow.getTitleText().contains("Заказ оформлен"));
    }
}

