package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage {

    @FindBy(xpath = "//h1")
    private SelenideElement logo;

    @FindBy(xpath = "//*[text()='Имя пользователя']")
    private SelenideElement loginInputText;

    @FindBy(xpath = "//*[@name='username']")
    private SelenideElement loginInput;

    @FindBy(xpath = "//*[@placeholder='Введите имя пользователя...']")
    private SelenideElement loginInputPlaceholder;

    @FindBy(xpath = "//*[text()='Пароль']")
    private SelenideElement passwordInputText;

    @FindBy(xpath = "//*[@id='password']")
    private SelenideElement passwordInput;

    @FindBy(xpath = "//*[@placeholder='Введите пароль...']")
    private SelenideElement passwordInputPlaceholder;

    @FindBy(xpath = "//*[text()='Повторите пароль']")
    private SelenideElement confirmPasswordInputText;

    @FindBy(xpath = "//*[@id='passwordSubmit']")
    private SelenideElement confirmPasswordInput;

    @FindBy(xpath = "//*[@placeholder='Повторите пароль...']")
    private SelenideElement confirmPasswordInputPlaceholder;

    @FindBy(xpath = "//*[contains(text(),'Зарегистрироваться')]")
    private SelenideElement submitButton;

    @FindBy(xpath = "//*[@class='form__paragraph']")
    private SelenideElement loginButton;

    @FindBy(xpath = "//*[@class='content__image']")
    private SelenideElement contentImage;

    @FindBy(xpath = "//*[@class='form__error error__username']")
    private SelenideElement nameValidationMessage;

    @FindBy(xpath = "//*[@class='form__error error__password']")
    private SelenideElement passwordValidationMessage;

    @FindBy(xpath = "//*[@class='form__subheader']")
    private SelenideElement subHeader;

    @FindBy(xpath = "//*[text()='Войти в систему']")
    private SelenideElement loginToSystemButton;

    public void checkAllRegistrationPageElementsAreVisible() {
        logo.shouldBe(visible).shouldHave(text("Rococo"));

        loginInputText.shouldBe(visible);
        loginInput.shouldBe(visible);
        loginInputPlaceholder.shouldBe(visible);

        passwordInputText.shouldBe(visible);
        passwordInput.shouldBe(visible);
        passwordInputPlaceholder.shouldBe(visible);

        confirmPasswordInputText.shouldBe(visible);
        confirmPasswordInput.shouldBe(visible);
        confirmPasswordInputPlaceholder.shouldBe(visible);

        submitButton.shouldBe(visible);

        loginButton.shouldBe(visible).shouldHave(text("Уже есть аккаунт? Войти"));

        contentImage.shouldBe(visible).shouldHave(attribute("src", "http://localhost:9000/images/renuar.jpeg"));
    }

    public RegisterPage setUsername(String username) {
        loginInput.sendKeys(username);
        return this;
    }

    public RegisterPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public RegisterPage setConfirmPassword(String password) {
        confirmPasswordInput.sendKeys(password);
        return this;
    }

    public RegisterPage tryToClickSubmitButton() {
        submitButton.click();
        return this;
    }

    public RegisterPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    public RegisterPage clickSuccessSubHeader() {
        String expectedText = "Добро пожаловать в Rococo";
        subHeader.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    public MainPage clickLoginToSystemButton() {
        loginToSystemButton.click();
        return page(MainPage.class);
    }

    public RegisterPage checkNameValidationMessage(String name) {
        String expectedText = "Username `" + name + "` already exist";
        nameValidationMessage.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    public RegisterPage checkPasswordValidationMessage() {
        String expectedText = "Passwords should be equal";
        passwordValidationMessage.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

}
