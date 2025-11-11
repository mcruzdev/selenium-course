package tech.ada;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    private final WebDriver driver;

    // Locators
    private final By firstNameInput = By.id("firstName");
    private final By lastNameInput = By.id("lastName");
    private final By emailInput = By.id("email");
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By registerButton = By.id("register");
    
    // Locators de Erro (Usando XPATH relativo para encontrar o span de erro logo após o input)
    private final String ERROR_SPAN_XPATH = "/following-sibling::span";
    
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToRegister(String baseUrl) {
        driver.get(baseUrl + "/register");
    }

    // Ação para REQ004 e REQ005/REQ006
    public void fillAndSubmitForm(String firstName, String lastName, String email, String username, String password) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(registerButton).click();
    }
    
    // Métodos de validação para REQ005 e REQ006
    public WebElement getErrorForField(String fieldId) {
        return driver.findElement(By.xpath("//input[@id='" + fieldId + "']" + ERROR_SPAN_XPATH));
    }
    
    // Método para erro de usuário já existente (REQ006) - Adaptável
    public WebElement getUsernameTakenMessage() {
        // Tentativa de localizar a mensagem pelo texto (Ajuste se o elemento for diferente)
        return driver.findElement(By.xpath("//*[contains(text(), 'That username is taken. Try another.')]")); 
    }
}