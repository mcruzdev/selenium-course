package tech.ada;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;

    // Locators
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By submitButton = By.xpath("//button[@type='submit']");
    private final By registerLink = By.xpath("//a[text()='Register here']");
    private final By errorMessage = By.xpath("//span[text()='Invalid username or password']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToLogin(String baseUrl) {
        driver.get(baseUrl + "/login");
    }

    public void login(String username, String password) {
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(submitButton).click();
    }
    
    // Métodos para REQ002 e REQ003
    public WebElement getRegisterLink() {
        return driver.findElement(registerLink);
    }
    
    public void clickRegisterLink() {
        getRegisterLink().click();
    }
    
    // Método para REQ007
    public WebElement getErrorMessage() {
        return driver.findElement(errorMessage);
    }
}