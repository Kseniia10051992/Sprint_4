import PO.CustomerInformation;
import PO.HomePageScooter;
import PO.OrderRegistrationWindow;
import PO.RentalInformation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.awt.Color.black;
import static java.awt.Color.gray;
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
                .choiceColour( black )
                .fieldComment( "Нет комментариев" )
                .clickFinishOrderButton();

        OrderRegistrationWindow orderRegistrationWindow = new OrderRegistrationWindow( driver );
        orderRegistrationWindow.clickButtonYes();
        assertTrue( orderRegistrationWindow.getTitleText().contains( "Заказ оформлен" ) );
    }

    @Test
    public void testBottomButtonOrder() {
        HomePageScooter homePageScooter = new HomePageScooter( driver );
        CustomerInformation customerInformation = new CustomerInformation( driver );
        RentalInformation rentalInformation = new RentalInformation( driver );

        homePageScooter.waitForLoadHomePageScooter()
                .clickTopOrderButton();

        homePageScooter.scrollToDownOrderButton()
                .scrollToDownOrderButton();

        customerInformation.waitForLoadOrderPage()
                .fieldName( "Ксения" )
                .fieldSurname( "Горбунова" )
                .fieldAddress( "улица Ленина" )
                .selectionStateMetro( 77 )
                .fieldPhone( "+79604567654" )
                .clickNextButton();
        rentalInformation.waitAboutRentHeader()
                .whenDate( "10.03.2024" )
                .rentalPeriod( "двое суток" )
                .choiceColour( gray )
                .fieldComment( "Просьба позвонить заранее" )
                .clickFinishOrderButton();

        OrderRegistrationWindow orderRegistrationWindow = new OrderRegistrationWindow( driver );
        orderRegistrationWindow.clickButtonYes();
        assertTrue( orderRegistrationWindow.getTitleText().contains( "Заказ оформлен" ) );
    }

    }


