package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    private static By OPINION_TAB = By.xpath("//a[text()='Opinión']");
    private static By ACCEPT_COOKIES = By.xpath("//span[text()='Aceptar' or contains(text(),'Accept')]//parent::button");

    public void clickOnOpinionTab() {
        driver.findElements(OPINION_TAB).get(1).click();
    }

    public void clickOnAcceptCookiesButton() {
        driver.findElement(ACCEPT_COOKIES).click();
    }
}
