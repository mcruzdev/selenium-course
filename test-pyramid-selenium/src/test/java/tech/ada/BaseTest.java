package tech.ada;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    // URL base da sua aplicação (Ajuste se necessário)
    protected final String BASE_URL = "http://localhost:8080"; 

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        // Espera implícita para a localização de elementos
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); 
        driver.manage().window().maximize(); 
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit(); 
        }
    }
}