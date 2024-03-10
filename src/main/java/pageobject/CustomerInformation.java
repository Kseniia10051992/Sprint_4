package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerInformation {
    WebDriver driver;
    private final By orderHeader = By.className("Order_Header__BZXOb");

    // Поле ввода "Имя":
    private final By nameField = By.xpath( ".//input[@placeholder='* Имя']" );

    // поле ввода "Фамилия":
    private final By surnameField = By.xpath( ".//input[@placeholder='* Фамилия']" );

    // поле ввода "Адрес":
    private final By fieldAddress = By.xpath( ".//input[@placeholder='* Адрес: куда привезти заказ']" );

    // Станция метро:
    private final By metroStation = By.className( "select-search__input" );

    // Наименование станции метро:
    private final String nameStateMetro = ".//button[@value='%s']";
    // Телефон:
    private final By phoneField = By.xpath( ".//input[@placeholder='* Телефон: на него позвонит курьер']" );

    // кнопка "Далее":
    private final By nextButton = By.xpath( ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']" );

    public CustomerInformation(WebDriver driver) {
        this.driver = driver;
    }

    //ожидание
    public CustomerInformation waitForLoadOrderPage() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(orderHeader).getText() != null
                && !driver.findElement(orderHeader).getText().isEmpty()
        ));
        return this;
    }

    public CustomerInformation fieldName(String newName) {
        driver.findElement( nameField ).sendKeys( newName );
        return this;
    }

    public CustomerInformation fieldSurname(String newSurname) {
        driver.findElement( surnameField ).sendKeys( newSurname );
        return this;
    }

    public CustomerInformation fieldAddress(String newAddress) {
        driver.findElement( fieldAddress).sendKeys( newAddress );
        return this;
    }

    public CustomerInformation selectionStateMetro(int stateNumber) {
        driver.findElement( metroStation ).click();
        By newStateMetro = By.xpath( String.format( nameStateMetro, stateNumber ) );
        ((JavascriptExecutor) driver).executeScript( "arguments[0].scrollIntoView();", driver.findElement( newStateMetro ) );
        driver.findElement( newStateMetro ).click();
        return this;
    }

    public CustomerInformation fieldPhone(String newPhone) {
        new WebDriverWait( driver, 10 ).until( ExpectedConditions.elementToBeClickable( phoneField ) );
        driver.findElement( phoneField ).sendKeys( newPhone );
        return this;
    }

    public void clickNextButton() {
        driver.findElement( nextButton ).click();
    }
}


