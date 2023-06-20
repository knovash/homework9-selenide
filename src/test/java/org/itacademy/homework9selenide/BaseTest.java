package org.itacademy.homework9selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.utils.Config;
import org.itacademy.homework9selenide.utils.WaitUtils;
import org.testng.annotations.*;

import java.util.logging.Level;

@Log4j2
public class BaseTest {

    @BeforeTest
    public void beforetest() {
        log.info("BEFORE TEST get properties");
        Config.getProperties();
    }

    @BeforeClass
    public void beforeclass() {
        log.info("BEFORE CLASS");
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
//                .enableLogs(LogType.BROWSER, Level.ALL)
                .screenshots(true)
        );

        Configuration.pageLoadTimeout = 1000000;
        Configuration.timeout = 1000000;
    }

    @AfterMethod
    public void aftermethod() {
        log.info("AFTER METHOD");
    }

    @AfterClass
    public void afterclass() {
        log.info("AFTER CLASS");

    }

    @AfterTest
    public void aftertest() {
        log.info("AFTER TEST");
        WaitUtils.waitSeconds(5);
    }
}
