package org.itacademy.homework9selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class OnlinerBuyTest {

    @DataProvider()
    public Object[][] itemsForSearch() {
        return new Object[][]{{"iphone 12"}};
    }

    @Description("Search item in catalog and compare first 10 items")
    @Test(testName = "CheckCompare", dataProvider = "itemsForSearch")
    public void onlnerCompareTest(String item) {
        log.info("OPEN PAGE ONLINER.BY");
        open("https://catalog.onliner.by/mobile/apple/iphone14");
        log.info("pageLoadTimeout: " + Configuration.pageLoadTimeout);
        log.info("timeout: " + Configuration.timeout);
        Configuration.pageLoadTimeout = 600000;
        Configuration.timeout = 600000;
        log.info("pageLoadTimeout: " + Configuration.pageLoadTimeout);
        log.info("timeout: " + Configuration.timeout);
        WebDriver driver = getWebDriver();

        SelenideElement title = $(By.xpath("//*[@class='catalog-masthead__title js-nav-header']"));
        log.info("DISPLAYED title: " + title.isDisplayed());
        log.info("HTML title: " + title.getAttribute("outerHTML"));
        log.info("TEXT title: " + title.text());

        SelenideElement buttonBuyNow = $(By.xpath("//*[@class='button-style button-style_adscititious button-style_base-alter product-aside__button product-aside__button_narrow product-aside__button_buy']"));
        log.info("DISPLAYED buttonBuyNow: " + buttonBuyNow.isDisplayed());
        log.info("HTML buttonBuyNow: " + buttonBuyNow.getAttribute("outerHTML"));
        log.info("TEXT buttonBuyNow: " + buttonBuyNow.text());
        buttonBuyNow.click();


        SelenideElement inputCity = $(By.xpath("//input[@placeholder='Укажите населенный пункт']"));
        SelenideElement clearCity = $(By.xpath("//input[@placeholder='Укажите населенный пункт']/following-sibling::div"));

        inputCity.shouldBe(visible);
        log.info("TEXT inputCity: " + inputCity.getValue());

        log.info("DISP clearCity: " + clearCity.isDisplayed());
//        inputCity.clear();
//        clearCity.click();
//        inputCity.sendKeys(Keys.BACK_SPACE);
        inputCity.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        inputCity.setValue("Брест");
        inputCity.shouldHave(value("Брест"));
        log.info("TEXT inputCity: " + inputCity.getValue());

        SelenideElement inputStreet = $(By.xpath("//*[contains(text(), 'Адрес доставки')]/following-sibling::div/following-sibling::div//input"));
        inputStreet.setValue("Кульман");
        inputStreet.shouldHave(value("Кульман"));
        log.info("TEXT inputStreet: " + inputStreet.getValue());

//
//        fastSearchInput.setValue(item);
//
//        SelenideElement frame = $(By.xpath("//iframe[@class='modal-iframe']"));
//        driver.switchTo().frame(frame);
//
//        SelenideElement checkBox = $(By.xpath("//*[@class='i-checkbox__faux']"));
//        checkBox.shouldBe(visible);
//////        SelenideElement checkBox = $(By.xpath("//input[@class='i-checkbox__real']"));
//        log.info("DISPLAYED: " + checkBox.isDisplayed());
//        log.info("HTML: " + checkBox.getAttribute("outerHTML"));
////        checkBox.click();
//////        checkBox.setSelected(true);
//
//        ElementsCollection checkBoxes = $$(By.xpath("//*[@class='i-checkbox__faux']"));
//
//        checkBoxes.stream().forEach(box -> box.click());
//
//        $(By.xpath("//div[@class='compare-button compare-button_visible']")).shouldBe(visible);
//        SelenideElement buttonCompare = $(By.xpath("//div[@class='compare-button compare-button_visible']"));
////        buttonCompare.click();
//
//        $(By.xpath("//*[@class='b-offers-title']")).shouldBe(visible);
//
//        SelenideElement titleCompare = $(By.xpath("//*[@class='b-offers-title']"));
//
//        log.info("DISPLAYED title: " + titleCompare.isDisplayed());
//        log.info("HTML title: " + titleCompare.getAttribute("outerHTML"));
//        log.info("TEXT title: " + titleCompare.text());
//
//        SelenideElement orange = $(By.xpath("//*[@class='button button_orange']"));
//        log.info("DISPLAYED orange: " + orange.isDisplayed());
//        log.info("HTML orange: " + orange.getAttribute("outerHTML"));
//        log.info("TEXT orange: " + orange.text());
//
//        SelenideElement product = $(By.xpath("//*[@class='product-summary']"));
//        log.info("DISPLAYED product: " + product.isDisplayed());
////        log.info("HTML product: " + product.getAttribute("outerHTML"));
//        log.info("TEXT product: " + product.text());
//        product.click();


        log.info("--==TEST END==--");

//        SelenideElement ppp = product.$(By.xpath("./*[@class='product-table__cell-inner product-table__cell-inner_overflow']"));
////        log.info("DISPLAYED ppp: " + ppp.isDisplayed());
//        log.info("HTML ppp: " + ppp.getAttribute("outerHTML"));
//        log.info("TEXT ppp: " + ppp.text());
////
//        products.stream()
////                .map(p -> p.$(By.xpath("./*[@class='button button_orange']")))
////                .forEach(p -> log.info(p.getText()));
//                .peek(p -> log.info(p.getAttribute("outerHTML")))
//                .map(p -> p);


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
    }
}
