package io.student.rococo.tests;

import com.github.javafaker.Faker;
import io.student.rococo.config.Config;
import io.student.rococo.jupiter.BrowserExtension;
import io.student.rococo.jupiter.User;
import io.student.rococo.model.UserJson;
import io.student.rococo.page.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;
import static io.student.rococo.constants.Constants.NOT_VALID_USER_PASSWORD;
import static io.student.rococo.constants.Constants.USER_PASSWORD;

@ExtendWith(BrowserExtension.class)
public class LoginTest {

    private static final Config CFG = Config.getInstance();
    Faker faker = new Faker();

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
                .setUsername(faker.name().username())
                .setPassword(USER_PASSWORD)
                .setConfirmPassword(USER_PASSWORD)
                .clickSubmitButton()
                .clickSuccessSubHeader()
                .clickLoginToSystemButton();
    }

    @Test
    @User
    @DisplayName("Пользователь не может зарегистрироваться, если логин существует")
    void shouldNotRegisterUserWithExistingUsername(UserJson userJson) {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .clickRegisterButton()
                .setUsername(userJson.username())
                .setPassword(USER_PASSWORD)
                .setConfirmPassword(USER_PASSWORD)
                .tryToClickSubmitButton()
                .checkNameValidationMessage(userJson.username());
    }

    @Test
    @DisplayName("Пользователь не может зарегистрироваться, если введенные пароли не совпадают")
    void shouldShowErrorIfPasswordAndConfirmPasswordAreNotEqual() {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .clickRegisterButton()
                .setUsername(faker.name().username())
                .setPassword(USER_PASSWORD)
                .setConfirmPassword(NOT_VALID_USER_PASSWORD)
                .tryToClickSubmitButton()
                .checkPasswordValidationMessage();
    }

    @Test
    @User
    @DisplayName("Зарегистрированный пользователь после успешного логина видит элементы главной страницы и аватар")
    void mainPageShouldBeDisplayedAfterSuccessLogin(UserJson userJson) {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .setUsername(userJson.username())
                .setPassword(USER_PASSWORD)
                .clickSubmitButton()
                .checkCommonMainPageWithAuthorizationElementsAreVisible();
    }

    @Test
    @User
    @DisplayName("Пользователь не может авторизоваться с неверными креденшилами")
    void userShouldStayOnLoginPageAfterLoginWithBadCredentials(UserJson userJson) {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .setUsername(userJson.username())
                .setPassword(NOT_VALID_USER_PASSWORD)
                .tryToClickSubmitButton()
                .checkCredentialValidationMessage();
    }

    @Test
    @User
    @DisplayName("Зарегистрированный пользователь после успешного логина видит элементы страницы 'Картины'")
    void museumPageShouldBeDisplayedAfterSuccessLogin(UserJson userJson) {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .setUsername(userJson.username())
                .setPassword(USER_PASSWORD)
                .clickSubmitButton()
                .clickPaintingsButton()
                .checkPaintingPageElementsAreVisible();
    }

    @Test
    @User
    @DisplayName("Зарегистрированный пользователь после успешного логина видит элементы страницы 'Художники'")
    void artistPageShouldBeDisplayedAfterSuccessLogin(UserJson userJson) {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .setUsername(userJson.username())
                .setPassword(USER_PASSWORD)
                .clickSubmitButton()
                .clickArtistsButton()
                .checkArtistsPageElementsAreVisible();
    }

    @Test
    @User
    @DisplayName("Зарегистрированный пользователь после успешного логина видит элементы страницы 'Музеи")
    void museumsPageShouldBeDisplayedAfterSuccessLogin(UserJson userJson) {
        open(CFG.frontUrl(), MainPage.class)
                .clickLoginButton()
                .setUsername(userJson.username())
                .setPassword(USER_PASSWORD)
                .clickSubmitButton()
                .clickMuseumsButton()
                .checkMuseumsPageElementsAreVisible();
    }

}
