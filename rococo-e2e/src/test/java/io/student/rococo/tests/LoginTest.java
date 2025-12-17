package io.student.rococo.tests;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    public final static String AUTOMATION_PRACTICE_FORM_URL = "/automation-practice-form";


    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void successfulSubmitFormTest() {
        open(AUTOMATION_PRACTICE_FORM_URL);
    }
}
