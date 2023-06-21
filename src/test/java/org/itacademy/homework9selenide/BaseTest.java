package org.itacademy.homework9selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class BaseTest {

    @BeforeClass
    public void beforeClass() {
        log.info("BEFORE CLASS");
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true)
        );
        // for mobile connection
        Configuration.pageLoadTimeout = 60000;
        Configuration.timeout = 60000;

    }
}
