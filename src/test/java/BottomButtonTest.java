import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.CustomerInformation;
import pageobject.HomePageScooter;

public class BottomButtonTest extends StartAndFinish {

    // 1 вариант

    @Test
    public void testBottomButtonOrder() {
        new HomePageScooter( driver )
                .waitForLoadHomePageScooter()
                .scrollToDownOrderButton()
                .clickBottomOrderButton();
        new CustomerInformation( driver )
                .checkingRealWindow();
    }

    // 2 вариант
    @Test
    public void testBottomButtonOrderTwo() {
        new HomePageScooter( driver )
                .waitForLoadHomePageScooter()
                .scrollToDownOrderButton()
                .clickBottomOrderButton();
        new CustomerInformation( driver )
                .waitForLoadOrderPage();
        if (driver.getPageSource().contains( "Для кого самокат" )) {
            System.out.println( "Успешно" );

        } else {
            System.out.println( "Не успешно" );
        }
    }

    // 3 вариант
        @Test
        public void testBottomButtonOrderThree() {
            new HomePageScooter( driver )
                    .waitForLoadHomePageScooter()
                    .scrollToDownOrderButton()
                    .clickBottomOrderButton();
            new WebDriverWait( driver, 10 )
                    .until( ExpectedConditions.visibilityOfElementLocated( By.className( "Order_Header__BZXOb" ) ) );
        }
    }










