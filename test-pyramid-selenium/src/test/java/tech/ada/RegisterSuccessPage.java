package tech.ada;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterSuccessPage {
    private final WebDriver driver;

    // Locators
    private final By successMessage = By.xpath("//p[text()='Registered with success!']");
    private final By backToLoginLink = By.xpath("//a[text()='Back to login']");

    public RegisterSuccessPage(WebDriver driver) {
        this.driver = driver;
    }
    
    // Métodos para REQ004
    public WebElement getSuccessMessage() {
        return driver.findElement(successMessage);
    }
    
    public WebElement getBackToLoginLink() {
        return driver.findElement(backToLoginLink);
    }
    
    public void clickBackToLogin() {
        getBackToLoginLink().click();
    }
}