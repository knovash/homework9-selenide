package org.itacademy.homework9selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class OnlinerCompareTest extends BaseTest {

    @DataProvider()
    public Object[][] itemsForSearch() {
        return new Object[][]{{"iphone 12"}};
    }

    @Description("Search item in catalog and compare first 10 items")
    @Test(testName = "CheckCompare", dataProvider = "itemsForSearch")
    public void onlnerCompareTest(String item) {
        log.info("OPEN PAGE https://www.onliner.by/");
        open("https://www.onliner.by/");
        WebDriver driver = getWebDriver();
        driver.manage().timeouts().pageLoadTimeout(600L, TimeUnit.SECONDS);

        SelenideElement fastSearchInput = $(By.xpath("//input[@class='fast-search__input']"));
        fastSearchInput.setValue(item);
        log.info("VALUE fastSearchInput: " + fastSearchInput.getValue());

        SelenideElement frame = $(By.xpath("//iframe[@class='modal-iframe']"));
        driver.switchTo().frame(frame);

        ElementsCollection checkBoxes = $$(By.xpath("//*[@class='i-checkbox__faux']"));
        checkBoxes.stream().forEach(box -> box.click());

        SelenideElement buttonCompare = $(By.xpath("//div[@class='compare-button compare-button_visible']"));
        buttonCompare.shouldBe(visible);
        buttonCompare.click();

        SelenideElement titleCompare = $(By.xpath("//*[@class='b-offers-title']"));
        titleCompare.shouldBe(visible);
        log.info("TEXT title: " + titleCompare.text());

        SelenideElement orange = $(By.xpath("//*[@class='button button_orange']"));
        orange.shouldBe(visible);
        log.info("TEXT orange: " + orange.text());

        ElementsCollection products = $$(By.xpath("//*[@class='product-table__cell']"));

        SelenideElement product = products.get(2);
        log.info("HTML product: " + product.getAttribute("outerHTML"));
        log.info("TEXT product: " + product.text());

// TODO неработает
        SelenideElement ppp = product.$(By.xpath("./*[@class='product-table__cell-inner product-table__cell-inner_overflow']"));
        log.info("HTML ppp: " + ppp.getAttribute("outerHTML"));
        log.info("TEXT ppp: " + ppp.text());

//        products.stream()
//                .map(p -> p.$(By.xpath("./*[@class='button button_orange']")))
//                .forEach(p -> log.info(p.getText()));
//                .peek(p -> log.info(p.getAttribute("outerHTML")))
//                .map(p -> p);

        log.info("--==TEST END==--");
    }
}
