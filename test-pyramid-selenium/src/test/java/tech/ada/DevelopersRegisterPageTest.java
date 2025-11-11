package tech.ada;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class DevelopersRegisterPageTest extends BaseTest {

    // REQ004: Cadastro com Sucesso (Caminho Feliz)
    @Test
    @DisplayName("REQ004 - Deve cadastrar um usuário com sucesso e verificar o redirecionamento")
    void deveCadastrarUsuarioComSucesso() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateToRegister(BASE_URL);

        // Preencher e submeter o formulário com dados válidos
        registerPage.fillAndSubmitForm(
                Constants.VALID_FIRST_NAME,
                Constants.VALID_LAST_NAME,
                Constants.VALID_EMAIL,
                Constants.VALID_USERNAME,
                Constants.VALID_PASSWORD
        );

        // Esperar pelo redirecionamento para a página de sucesso
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(urlContains("/register-successfully"));

        RegisterSuccessPage successPage = new RegisterSuccessPage(driver);

        // Verifica a mensagem de sucesso
        Assertions.assertTrue(successPage.getSuccessMessage().isDisplayed(),
                "A mensagem 'Registered with success!' deve ser exibida.");

        // Verifica o link "Back to login"
        Assertions.assertTrue(successPage.getBackToLoginLink().isDisplayed(),
                "O link 'Back to login' deve ser exibido na página de sucesso.");

        // Clica no link e verifica o retorno ao login
        successPage.clickBackToLogin();
        wait.until(urlToBe(BASE_URL + "/login"));
        Assertions.assertEquals(BASE_URL + "/login", driver.getCurrentUrl(),
                "O link 'Back to login' deve retornar para a página de login.");
    }

    // REQ005: Validação de Formulário
    @Test
    @DisplayName("REQ005 - Deve exibir mensagens de erro para submissão com dados inválidos")
    void deveValidarFormularioComDadosInvalidos() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateToRegister(BASE_URL);

        // Preencher com dados inválidos/vazios
        registerPage.fillAndSubmitForm(
                Constants.INVALID_SHORT_DATA, // First name < 2
                "", // Last name vazio
                Constants.INVALID_EMAIL, // Email inválido
                Constants.INVALID_SHORT_DATA, // Username < 2
                Constants.INVALID_SHORT_PASSWORD // Password < 8
        );

        // Verificar as mensagens de erro (REQ005)
        Assertions.assertTrue(registerPage.getErrorForField("firstName").isDisplayed(), "Erro para 'First name' não exibido.");
        Assertions.assertTrue(registerPage.getErrorForField("lastName").isDisplayed(), "Erro para 'Last name' não exibido.");
        Assertions.assertTrue(registerPage.getErrorForField("email").isDisplayed(), "Erro para 'Email' não exibido.");
        Assertions.assertTrue(registerPage.getErrorForField("username").isDisplayed(), "Erro para 'Username' não exibido.");
        Assertions.assertTrue(registerPage.getErrorForField("password").isDisplayed(), "Erro para 'Password' não exibido.");

        // Verificar que a URL não mudou
        Assertions.assertEquals(BASE_URL + "/register", driver.getCurrentUrl(),
                "A URL deve permanecer na página de cadastro em caso de erros.");
    }

    // REQ006: Cadastro de Usuário Existente
    @Test
    @DisplayName("REQ006 - Deve exibir mensagem de erro ao cadastrar usuário já existente")
    void deveExibirErroAoCadastrarUsuarioExistente() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.navigateToRegister(BASE_URL);

        // Preencher com dados de um usuário que **já deve existir** (definido em Constants)
        registerPage.fillAndSubmitForm(
                Constants.VALID_FIRST_NAME,
                Constants.VALID_LAST_NAME,
                Constants.EXISTING_EMAIL,
                Constants.EXISTING_USERNAME,
                Constants.VALID_PASSWORD
        );

        // Verificar a mensagem de erro específica
        // É necessário esperar a mensagem de erro aparecer, pois é um retorno do servidor
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(visibilityOf(registerPage.getUsernameTakenMessage()));

        Assertions.assertTrue(registerPage.getUsernameTakenMessage().isDisplayed(),
                "A mensagem 'That username is taken. Try another.' deve ser exibida.");

        Assertions.assertEquals(BASE_URL + "/register", driver.getCurrentUrl(),
                "A URL não deve mudar (não deve ir para a página de sucesso).");
    }
}