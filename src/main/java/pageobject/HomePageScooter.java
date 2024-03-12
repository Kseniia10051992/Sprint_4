package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static сonstants.ОrderButton.BOTTOM_BUTTON;
import static сonstants.ОrderButton.TOP_BUTTON;


public class HomePageScooter {
    WebDriver driver;

    private final By homeHeader = By.className( "Home_Header__iJKdX" );

    // верхняя кнопка "Заказать"
    private final By topOrderButton = By.className( "Button_Button__ra12g" );

    // нижняя кнопка "Заказать"
    private final By bottomOrderButton = By.xpath( ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']" );
    // Вопросы о важном
    private final By questionsHeader = By.className( "Home_FourPart__1uthg" );


    public HomePageScooter(WebDriver driver) {

        this.driver = driver;
    }

    //метод ожидания загрузки главной страницы
    public HomePageScooter waitForLoadHomePageScooter() {
        new WebDriverWait( driver, 10 ).until( driver -> (driver.findElement( homeHeader ).getText() != null
                && !driver.findElement( homeHeader ).getText().isEmpty()
        ) );
        return this;
    }

    //метод прокрутки к нижней кнопке "Заказать"
    public HomePageScooter scrollToDownOrderButton() {
        ((JavascriptExecutor) driver).executeScript( "arguments[0].scrollIntoView();", driver.findElement( bottomOrderButton ) );
        return this;
    }

    //метод прокрутки к блоку "Вопросы о важном"
    public HomePageScooter scrollToQuestions() {
        ((JavascriptExecutor) driver).executeScript( "arguments[0].scrollIntoView();", driver.findElement( questionsHeader ) );
        return this;
    }

    //метод ожидания
    public void waitLoadAfterClickQuestion(By accordionLabel) {
        new WebDriverWait( driver, 10 ).until( driver -> (driver.findElement( accordionLabel ).getText() != null
                && !driver.findElement( accordionLabel ).getText().isEmpty()
        ) );
    }

    public void clickCreateOrderButton(String button) {
        if (button.equals(TOP_BUTTON)) {
            clickTopOrderButton();
        } else if (button.equals(BOTTOM_BUTTON)) {
            scrollToDownOrderButton();
            clickBottomOrderButton();
        }
    }

    public HomePageScooter clickTopOrderButton() {
        driver.findElement( topOrderButton ).click();
        return this;
    }

    public  HomePageScooter clickBottomOrderButton() {
        driver.findElement( bottomOrderButton ).click();
        return this;
    }


    public HomePageScooter questionsHeader(By question) {
        new WebDriverWait( driver, 10 )
                .until( ExpectedConditions.elementToBeClickable( question ) )
                .click();
        return this;

    }
}



