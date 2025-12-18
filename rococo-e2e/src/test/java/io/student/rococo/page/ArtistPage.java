package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;

public class ArtistPage {

    @FindBy(xpath = "//h1/a")
    private SelenideElement logo;

    @FindBy(xpath = "//h2")
    private SelenideElement header;

    @FindBy(xpath = "//*[text()='Добавить художника']")
    private SelenideElement addArtistButton;

    public void checkArtistsPageElementsAreVisible() {
        logo.shouldBe(visible).shouldHave(text("Rococo"));
        header.shouldBe(visible).shouldHave(text("Художники"));
        addArtistButton.shouldBe(visible).shouldBe(clickable);
    }

}
