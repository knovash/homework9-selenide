package org.itacademy.homework9selenide.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WaitUtils {

    public static void waitForVisibility(WebElement element, int seconds) {
        new WebDriverWait(getWebDriver(), Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitSeconds(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {

        }
    }
}