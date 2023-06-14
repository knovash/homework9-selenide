package org.itacademy.homework9selenide;

import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.utils.Config;
import org.testng.annotations.*;

@Log4j2
public class BaseTest {

    @BeforeTest
    public void beforetest() {
        log.info("BEFORE TEST get properties");
        Config.getProperties();
    }

    @BeforeClass
    public void beforeclass() {
        log.info("BEFORE CLASS do nothing");
    }

    @AfterMethod
    public void aftermethod() {
        log.info("AFTER METHOD do nothing");
    }

    @AfterClass
    public void afterclass() {
        log.info("AFTER CLASS do nothing");
    }

    @AfterTest
    public void aftertest() {
        log.info("AFTER TEST do nothing");
    }
}
