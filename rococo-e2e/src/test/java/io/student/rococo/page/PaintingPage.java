package io.student.rococo.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;

public class PaintingPage {

    @FindBy(xpath = "//h1/a")
    private SelenideElement logo;

    @FindBy(xpath = "//h2")
    private SelenideElement header;

    @FindBy(xpath = "//*[text()='Добавить картину']")
    private SelenideElement addPaintingButton;

    public void checkPaintingPageElementsAreVisible() {
        logo.shouldBe(visible).shouldHave(text("Rococo"));
        header.shouldBe(visible).shouldHave(text("Картины"));
        addPaintingButton.shouldBe(visible).shouldBe(clickable);
    }

}
