package org.itacademy.homework9selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
@Listeners
public class OnlinerCheapestProductClosestTest extends BaseTest {

    @Description("Search item in catalog and compare first 10 items")
    @Test(testName = "CheckCompare")
    public void onlnerCompareTest() {
        log.info("OPEN PAGE https://www.onliner.by/");
        open("https://www.onliner.by/");
        WebDriver driver = getWebDriver();
        driver.manage().timeouts().pageLoadTimeout(600L, TimeUnit.SECONDS);

        SelenideElement fastSearchInput = $(By.xpath("//input[@class='fast-search__input']"));
        WaitUtils.waitForVisibility(fastSearchInput, 900);
        fastSearchInput.setValue("iphone 12");
        log.info("VALUE fastSearchInput: " + fastSearchInput.getValue());

        SelenideElement frame = $(By.xpath("//iframe[@class='modal-iframe']"));
        WaitUtils.waitForVisibility(frame, 900);
        driver.switchTo().frame(frame);

        WaitUtils.waitForVisibility($(By.xpath("//*[@class='search__result']")), 900);
        ElementsCollection searchResults = $$(By.xpath("//div[@class='product__price']//span"));

        log.info("FIND CHEPEST PRODUCT...");
        SelenideElement cheapestProduct = findCheapestProduct(searchResults);

        SelenideElement title = cheapestProduct.$(By.xpath(".//div[@class='product__title']"));
        String titleText = title.getText();
        log.info("TITLE TEXT: " + titleText);
        SelenideElement price = cheapestProduct.$(By.xpath(".//div[@class='product__price']//span"));
        String priceText = price.getText();
        log.info("PRICE TEXT: " + priceText);

    }

    private SelenideElement findCheapestProduct(ElementsCollection productPrices) {
        log.info("SIZE " + productPrices.size());
        double lowestPrice = Double.MAX_VALUE;
        SelenideElement cheapestProduct = null;

        for (SelenideElement productPrice : productPrices) {
            log.info("PRICE TEXT: " + productPrice.getText());
            String priceText = productPrice.getText()
                    .replace(" р.", "")
                    .replace(",", ".");
            double price = Double.parseDouble(priceText);
            log.info("PRICE DOUBLE: " + price);
            if (price < lowestPrice) {
                lowestPrice = price;
                cheapestProduct = productPrice.closest("div[contains(@class,'result__wrapper')]");
            }
            log.info("PRICE LOWEST: " + lowestPrice);
        }
        return cheapestProduct;
    }
}
