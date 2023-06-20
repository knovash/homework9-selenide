package org.itacademy.homework9selenide.utils;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class WaitUtils {

    public static void waitForVisibility(SelenideElement element, int seconds) {
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitSeconds(int time) {
        log.info("WAIT sec: " + time);
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {

        }
    }
}