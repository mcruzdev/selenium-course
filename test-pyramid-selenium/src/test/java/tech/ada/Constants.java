package tech.ada;

public class Constants {

    // Dados para REQ004 (Caminho Feliz)
    public static final String VALID_FIRST_NAME = "Selenium";
    public static final String VALID_LAST_NAME = "Testes";
    public static final String VALID_EMAIL = "selenium.happy@ada.com";
    public static final String VALID_USERNAME = "seleniumhappy";
    public static final String VALID_PASSWORD = "Password12345";

    // Dados para REQ006 (Usuário Existente) - Deve ser o mesmo do seu banco de dados
    public static final String EXISTING_USERNAME = "usuarioexistente";
    public static final String EXISTING_EMAIL = "existente@ada.com";

    // Dados para REQ005 (Validação - Dados Inválidos)
    public static final String INVALID_SHORT_DATA = "a"; // < 2 chars
    public static final String INVALID_EMAIL = "emailinvalido";
    public static final String INVALID_SHORT_PASSWORD = "short"; // < 8 chars
}