package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;

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

    @FindBy(xpath = "//*[@name='password']")
    private SelenideElement passwordInput;

    @FindBy(xpath = "//*[@placeholder='Введите пароль...']")
    private SelenideElement passwordInputPlaceholder;

    @FindBy(xpath = "//*[text()='Повторите пароль']")
    private SelenideElement confirmPasswordInputText;

    @FindBy(xpath = "//*[@name='password']")
    private SelenideElement confirmPasswordInput;

    @FindBy(xpath = "//*[@placeholder='Повторите пароль...']")
    private SelenideElement confirmPasswordInputPlaceholder;

    @FindBy(xpath = "//*[text()='Зарегистрироваться']")
    private SelenideElement submitButton;

    @FindBy(xpath = "//*[@class='form__paragraph']")
    private SelenideElement loginButton;

    @FindBy(xpath = "//*[@class='content__image']")
    private SelenideElement contentImage;

    private void checkAllRegistrationPageElementsAreVisible() {
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

        contentImage.shouldBe(visible).shouldHave(attribute("src", "/images/renuar.jpeg"));
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


    public RegisterPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public RegisterPage clickLoginButton() {
        loginButton.click();
        return this;
    }

}
