package io.student.rococo.tests;

import io.student.rococo.config.Config;
import io.student.rococo.page.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    private static final Config CFG = Config.getInstance();
    private final String USER = "duck";
    private final String NEW_USER = "duck5";
    private final String PASSWORD = "123456";
    private final String NOT_VALID_PASSWORD = "1234567";

    @Test
    @DisplayName("Главная страница отображается без авторизации")
    void shouldBeMaimPageVisibleWithoutAuthorization() {
        open(CFG.frontUrl(), MainPage.class)
                .checkCommonMainPageWithoutAuthorizationElementsAreVisible();
    }

    @Test
    @DisplayName("Все элементы на странице регистрации отображаются")
    void shouldBeAllElementsRegisterPageAreVisible() {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .clickRegisterButton()
                .checkAllRegistrationPageElementsAreVisible();
    }

    @Test
    @DisplayName("Пользователь может зарегистрироваться и увидеть элементы главной страницы и аватар")
    void shouldBeRegisterNewUser() {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .clickRegisterButton()
                .setUsername(NEW_USER)
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD)
                .clickSubmitButton()
                .clickSuccessSubHeader()
                .clickLoginToSystemButton();
    }

    @Test
    @DisplayName("Пользователь не может зарегистрироваться, если логин существует")
    void shouldNotRegisterUserWithExistingUsername() {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .clickRegisterButton()
                .setUsername(USER)
                .setPassword(PASSWORD)
                .setConfirmPassword(PASSWORD)
                .tryToClickSubmitButton()
                .checkNameValidationMessage(USER);
    }

    @Test
    @DisplayName("Пользователь не может зарегистрироваться, если введенные пароли не совпадают")
    void shouldShowErrorIfPasswordAndConfirmPasswordAreNotEqual() {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .clickRegisterButton()
                .setUsername(USER)
                .setPassword(PASSWORD)
                .setConfirmPassword(NOT_VALID_PASSWORD)
                .tryToClickSubmitButton()
                .checkPasswordValidationMessage();
    }

    @Test
    @DisplayName("Зарегистрированный пользователь после успешного логина видит элементы главной страницы и аватар")
    void mainPageShouldBeDisplayedAfterSuccessLogin() {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .setUsername(USER)
                .setPassword(PASSWORD)
                .clickSubmitButton()
                .checkCommonMainPageWithAuthorizationElementsAreVisible();
    }

    @Test
    @DisplayName("Пользователь не может авторизоваться с неверными креденшилами")
    void userShouldStayOnLoginPageAfterLoginWithBadCredentials() {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .setUsername(USER)
                .setPassword(NOT_VALID_PASSWORD)
                .tryToClickSubmitButton()
                .checkCredentialValidationMessage();
    }

    @Test
    @DisplayName("Зарегистрированный пользователь после успешного логина видит элементы страницы 'Картины'")
    void museumPageShouldBeDisplayedAfterSuccessLogin() {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .setUsername(USER)
                .setPassword(PASSWORD)
                .clickSubmitButton()
                .clickPaintingsButton()
                .checkPaintingPageElementsAreVisible();
    }

    @Test
    @DisplayName("Зарегистрированный пользователь после успешного логина видит элементы страницы 'Художники'")
    void artistPageShouldBeDisplayedAfterSuccessLogin() {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .setUsername(USER)
                .setPassword(PASSWORD)
                .clickSubmitButton()
                .clickArtistsButton()
                .checkArtistsPageElementsAreVisible();
    }

    @Test
    @DisplayName("Зарегистрированный пользователь после успешного логина видит элементы страницы 'Музеи")
    void museumsPageShouldBeDisplayedAfterSuccessLogin() {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .setUsername(USER)
                .setPassword(PASSWORD)
                .clickSubmitButton()
                .clickMuseumsButton()
                .checkMuseumsPageElementsAreVisible();
    }
}
