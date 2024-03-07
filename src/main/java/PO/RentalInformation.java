package PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;

import static java.awt.Color.*;
import static org.openqa.selenium.support.Colors.GREY;

public class RentalInformation {
    WebDriver driver;

    // Поле "Когда привезти самокат":
    private final By whenToBringTheScooter = By.xpath( ".//input[@placeholder='* Когда привезти самокат']" );

    // Поле "Срок аренды":
    private final By rentalPeriod = By.xpath( ".//span[@class='Dropdown-arrow']" );

    // Цвет самоката "чёрный жемчуг":
    private final By colourBlack = By.id( "black" );

    // Цвет самоката "серая безысходность":
    private final By colourGrey = By.id( "grey" );

    // Поле ввода "Комментарий для курьера":
    private final By commentForCourier = By.xpath( ".//input[@placeholder='Комментарий для курьера']" );

    // Кнопка "Заказать" для окончательного заказа:
    private final By finishOrderButton = By.xpath( ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']" );

    // текст "Заказ оформлен":
    private final By orderText = By.className( "Order_Header__BZXOb" );

    public RentalInformation(WebDriver driver) {
        this.driver = driver;
    }

    //метод ожидания загрузки страницы
    public RentalInformation waitAboutRentHeader() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(orderText).getText() != null
                && !driver.findElement(orderText).getText().isEmpty()
        ));
        return this;
    }

    public RentalInformation whenDate(String newDate) {
        driver.findElement( whenToBringTheScooter ).sendKeys( newDate );
        return this;
    }

    public RentalInformation rentalPeriod(String newPeriod) {
        driver.findElement( rentalPeriod ).click();
        new WebDriverWait( driver, 10).until( ExpectedConditions.elementToBeClickable( By.className( "Dropdown-menu" ) ) ).click();
        return this;
    }
    public RentalInformation choiceColour(String Colour) {
        if (Colour.equals(BLACK)) {
            driver.findElement(colourBlack).click();
        } else if (Colour.equals(GRAY)) {
            driver.findElement(colourGrey).click();
        }
        return this;
    }

    public RentalInformation fieldComment(String newComment) {
        driver.findElement( commentForCourier ).sendKeys( newComment );
        return this;
    }

    public void clickFinishOrderButton() {
        driver.findElement( finishOrderButton ).click();
    }
}

