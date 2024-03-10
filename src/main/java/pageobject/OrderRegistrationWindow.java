package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderRegistrationWindow {
    WebDriver driver;
    private final By inscriptionOrderCompleted = By.xpath(".//div[text()='Заказ оформлен']");
    private final By buttonYes = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    public OrderRegistrationWindow(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonYes() {
        new WebDriverWait(driver, 10)
                .until( ExpectedConditions.elementToBeClickable(buttonYes)).click();
    }

    public String getTitleText() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(inscriptionOrderCompleted).getText() != null
                && !driver.findElement(inscriptionOrderCompleted).getText().isEmpty()
        ));
        return driver.findElement(inscriptionOrderCompleted).getText();
    }
}

