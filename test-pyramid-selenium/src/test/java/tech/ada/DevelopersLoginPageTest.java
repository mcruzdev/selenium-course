package tech.ada;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DevelopersLoginPageTest extends BaseTest {

    // REQ001: Título da Aplicação
    @Test
    @DisplayName("REQ001 - Deve verificar o título da página de login")
    void deveVerificarTituloDaPaginaDeLogin() {
        driver.get(BASE_URL + "/login");
        Assertions.assertEquals("Developers - Selenium Labs", driver.getTitle(),
                "O título da página deve ser 'Developers - Selenium Labs'");
    }

    // REQ002 e REQ003: Link de Cadastro
    @Test
    @DisplayName("REQ002 & REQ003 - Deve visualizar e usar o link 'Register here'")
    void deveRedirecionarParaOCadastro() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLogin(BASE_URL);

        // REQ002: Verifica se o link está visível
        Assertions.assertTrue(loginPage.getRegisterLink().isDisplayed(),
                "O link 'Register here' deve estar visível.");
        Assertions.assertEquals("Register here", loginPage.getRegisterLink().getText(),
                "O texto do link está incorreto.");

        // REQ003: Clica no link e verifica o redirecionamento
        loginPage.clickRegisterLink();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe(BASE_URL + "/register"));

        Assertions.assertEquals(BASE_URL + "/register", driver.getCurrentUrl(),
                "Deve ser redirecionado para a página de cadastro (/register)");
    }

    // REQ007: Login com Credenciais Erradas
    @Test
    @DisplayName("REQ007 - Deve exibir mensagem de erro ao realizar login com credenciais erradas")
    void deveFalharLoginComCredenciaisErradas() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLogin(BASE_URL);

        // Preencher com credenciais erradas e submeter
        loginPage.login("usuario-inexistente", "senha-invalida");

        // O teste deve permanecer na página de login e a mensagem deve ser visível
        Assertions.assertTrue(loginPage.getErrorMessage().isDisplayed(),
                "A mensagem de erro 'Invalid username or password' deve ser exibida.");

        // Verifica que o login falhou e não foi para a home
        Assertions.assertFalse(driver.getCurrentUrl().contains("/home"),
                "O login deve falhar e o usuário não deve ser redirecionado para a home.");
    }
}