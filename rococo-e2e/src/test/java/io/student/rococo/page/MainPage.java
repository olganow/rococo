package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    @FindBy(xpath = "//h1")
    private SelenideElement logo;

    @FindBy(xpath = "//a[text()='Картины']")
    private SelenideElement toolbarPicturesButton;

    @FindBy(xpath = "//a[text()='Художники']")
    private SelenideElement toolbarArtistsButton;

    @FindBy(xpath = "//a[text()='Музеи']")
    private SelenideElement toolbarMuseumsButton;

    @FindBy(xpath = "//*[@role='switch']")
    private SelenideElement toolbarSwitchButton;

    @FindBy(xpath = "//*[text()='Войти']")
    private SelenideElement loginButton;

    @FindBy(xpath = "//*[contains(text(),'Ваши любимые')]")
    private SelenideElement header;

    @FindBy(xpath = "//div[text()='Картины']")
    private SelenideElement mainPageMenuPicturesButton;

    @FindBy(xpath = "//div[text()='Художники']")
    private SelenideElement mainPageMenuArtistsButton;

    @FindBy(xpath = "//div[text()='Музеи']")
    private SelenideElement mainPageMenuMuseumsButton;

    @FindBy(xpath = "//*[@data-testid='avatar']")
    private SelenideElement avatarButton;


    public MainPage checkCommonMainPageWithoutAuthorizationElementsAreVisible() {
        checkCommonMainPageElementsAreVisible();
        loginButton.shouldBe(visible);
        return this;
    }

    public MainPage checkCommonMainPageWithAuthorizationElementsAreVisible() {
        checkCommonMainPageElementsAreVisible();
        avatarButton.shouldBe(visible);
        return this;
    }

    private void checkCommonMainPageElementsAreVisible() {
        logo.shouldBe(visible).shouldHave(text("Rococo"));
        toolbarPicturesButton.shouldBe(visible);
        toolbarArtistsButton.shouldBe(visible);
        toolbarMuseumsButton.shouldBe(visible);

        toolbarSwitchButton.shouldBe(visible);

        header.shouldBe(visible).shouldHave(text("Ваши любимые картины и художники всегда рядом"));
        mainPageMenuPicturesButton.shouldBe(visible);
        mainPageMenuArtistsButton.shouldBe(visible);
        mainPageMenuMuseumsButton.shouldBe(visible);
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    public PaintingPage clickPaintingsButton() {
        mainPageMenuPicturesButton.click();
        return page(PaintingPage.class);
    }

    public ArtistPage clickArtistsButton() {
        mainPageMenuArtistsButton.click();
        return page(ArtistPage.class);
    }

    public MuseumPage clickMuseumsButton() {
        mainPageMenuMuseumsButton.click();
        return page(MuseumPage.class);
    }

}
