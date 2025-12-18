package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

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

    @FindBy(xpath = "//*[@name='password']")
    private SelenideElement passwordInput;

    @FindBy(xpath = "//*[@placeholder='Введите пароль...']")
    private SelenideElement passwordInputPlaceholder;

    @FindBy(xpath = "//*[@class='form__password-button']")
    private SelenideElement formPasswordButton;

    @FindBy(xpath = "//*[contains(text(),'Войти')]")
    private SelenideElement submitButton;

    @FindBy(xpath = "//*[@class='form__paragraph']")
    private SelenideElement registerButton;

    @FindBy(xpath = "//*[@class='content__image']")
    private SelenideElement contentImage;

    @FindBy(xpath = "//*[@class='form__error login__error']")
    private SelenideElement credentialValidationMessage;

    public void checkAllLoginPageElementsAreVisible() {
        logo.shouldBe(visible).shouldHave(text("Rococo"));

        loginInputText.shouldBe(visible);
        loginInput.shouldBe(visible);
        loginInputPlaceholder.shouldBe(visible);

        passwordInputText.shouldBe(visible);
        passwordInput.shouldBe(visible);
        passwordInputPlaceholder.shouldBe(visible);
        formPasswordButton.shouldBe(visible);

        submitButton.shouldBe(visible);

        registerButton.shouldBe(visible).shouldHave(text("Нет аккаунта? Зарегистрироваться"));

        contentImage.shouldBe(visible).shouldHave(attribute("src", "/images/hermitage.jpg"));
    }

    public LoginPage setUsername(String username) {
        loginInput.sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage tryToClickSubmitButton() {
        submitButton.click();
        return this;
    }

    public LoginPage checkCredentialValidationMessage() {
        String expectedText = "Bad credentials";
        credentialValidationMessage.shouldBe(visible).shouldHave(text(expectedText));
        return this;
    }

    public MainPage clickSubmitButton() {
        submitButton.click();
        return page(MainPage.class);
    }

    public RegisterPage clickRegisterButton() {
        registerButton.click();
        return page(RegisterPage.class);
    }

}
