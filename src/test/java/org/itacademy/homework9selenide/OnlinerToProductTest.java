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

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class OnlinerToProductTest {

    @DataProvider()
    public Object[][] itemsForSearch() {
        return new Object[][]{{"iphone 12"}};
    }

    @Description("Search item in catalog and compare first 10 items")
    @Test(testName = "CheckCompare", dataProvider = "itemsForSearch")
    public void onlnerCompareTest(String item) {
        log.info("OPEN PAGE https://www.onliner.by/");
        open("https://www.onliner.by/");

        log.info("pageLoadTimeout: " + Configuration.pageLoadTimeout);
        log.info("timeout: " + Configuration.timeout);
        Configuration.pageLoadTimeout = 600000;
        Configuration.timeout = 600000;
        log.info("pageLoadTimeout: " + Configuration.pageLoadTimeout);
        log.info("timeout: " + Configuration.timeout);
        WebDriver driver = getWebDriver();
        SelenideElement fastSearchInput = $(By.xpath("//input[@class='fast-search__input']"));
        log.info("DISPLAYED: " + fastSearchInput.isDisplayed());
        log.info("HTML: " + fastSearchInput.getAttribute("outerHTML"));

        fastSearchInput.setValue(item);

        SelenideElement frame = $(By.xpath("//iframe[@class='modal-iframe']"));
        driver.switchTo().frame(frame);

        SelenideElement checkBox = $(By.xpath("//*[@class='i-checkbox__faux']"));
        checkBox.shouldBe(visible);
////        SelenideElement checkBox = $(By.xpath("//input[@class='i-checkbox__real']"));
        log.info("DISPLAYED: " + checkBox.isDisplayed());
        log.info("HTML: " + checkBox.getAttribute("outerHTML"));
//        checkBox.click();
////        checkBox.setSelected(true);

        ElementsCollection checkBoxes = $$(By.xpath("//*[@class='i-checkbox__faux']"));

        checkBoxes.stream().forEach(box -> box.click());

        $(By.xpath("//div[@class='compare-button compare-button_visible']")).shouldBe(visible);
        SelenideElement buttonCompare = $(By.xpath("//div[@class='compare-button compare-button_visible']"));
        buttonCompare.click();

        $(By.xpath("//*[@class='b-offers-title']")).shouldBe(visible);

        SelenideElement titleCompare = $(By.xpath("//*[@class='b-offers-title']"));

        log.info("DISPLAYED title: " + titleCompare.isDisplayed());
        log.info("HTML title: " + titleCompare.getAttribute("outerHTML"));
        log.info("TEXT title: " + titleCompare.text());

        SelenideElement orange = $(By.xpath("//*[@class='button button_orange']"));
        log.info("DISPLAYED orange: " + orange.isDisplayed());
        log.info("HTML orange: " + orange.getAttribute("outerHTML"));
        log.info("TEXT orange: " + orange.text());

        SelenideElement product = $(By.xpath("//*[@class='product-summary']"));
        log.info("DISPLAYED product: " + product.isDisplayed());
//        log.info("HTML product: " + product.getAttribute("outerHTML"));
        log.info("TEXT product: " + product.text());
        product.click();

        log.info("--==TEST END==--");
    }
}
