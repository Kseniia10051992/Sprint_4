import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.HomePageScooter;

import static org.junit.Assert.assertEquals;
import static сonstants.ОrderButton.BOTTOM_BUTTON;

public class BottomButtonTest extends StartAndFinish {
    WebDriver driver;

    @Override
    public void setUp() {
        driver = new ChromeDriver();
        driver.get( "https://qa-scooter.praktikum-services.ru/" );
        driver.manage().window().maximize();
    }

    @Override
    public void tearDown() {
        driver.quit();
    }

//Второй флоу ( нижняя кнопка) - тестируем до появления формы ввода параметров заказа)
// Не совсем понимаю, это ли имеется ввиду(( или как реализовать

    @Test
    public void testBottomButtonOrder() {
        new HomePageScooter(driver)
                .waitForLoadHomePageScooter()
                .scrollToDownOrderButton()
                .clickBottomOrderButton();

    }
}
