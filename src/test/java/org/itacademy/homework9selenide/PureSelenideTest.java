package org.itacademy.homework9selenide;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class PureSelenideTest {

    @Description("Verifys search result items")
    @Test(testName = "SelenideTest")
    public void pureSelenideTest() {
        log.info("TEST SEARCH");
        open("https://donerking.by/");
        log.info("CLICK SEARCH BUTTON");
        $(By.xpath("//a[@class='main-header__search-block']//*[name()='svg']")).click();
        log.info("ENTER TEXT IN SEARCH FIELD");
        $(By.xpath("//input[@class='search-header__input']")).setValue("бургер");
        log.info("SUBMIT SEARCH");
        $(By.xpath("//input[@class='search-header__input']")).submit();
        log.info("GET RESULT COLLECTION");
        ElementsCollection resultElements = $$(By.xpath("//div[@class='col-xxs-12 col-xs-6 col-sm-4 col-md-4 col-lg-4 menuItemWrapper']//div[@class='imageData']//div[@class='h4']"));
        resultElements.stream()
                .map(selenideElement -> selenideElement.getText().toLowerCase())
                .peek(text -> log.info("ITEM :" + text))
                .forEach(text -> Assert.assertTrue(text.contains("бургер"), "not match"));
        log.info("TEST END");
    }
}
