package org.itacademy.homework9selenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class PureSelenideTest2 {

    @Description("Verifys search result items")
    @Test(testName = "SelenideTest")
    public void pureSelenideTest() {
        log.info("OPEN PAGE");
        open("https://donerking.by/");
        $(By.xpath("//*[@class='icon icn-search']")).shouldBe(visible);
        SelenideElement searchButton = $(By.xpath("//*[@class='icon icn-search']"));
        log.info("HTML: " + searchButton.getAttribute("outerHTML"));
        log.info("DISPLAYED: " + searchButton.isDisplayed());
        searchButton.click();

        SelenideElement searchField = $(By.xpath("//input[@class='search-header__input']"));
        searchField.setValue("test");
        log.info("HTML: " + searchField.getAttribute("outerHTML"));
        log.info("VALUE: " + searchField.getValue());
        log.info("DISPLAYED: " + searchField.isDisplayed());
        searchField.shouldHave(value("test"));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }




    }
}
