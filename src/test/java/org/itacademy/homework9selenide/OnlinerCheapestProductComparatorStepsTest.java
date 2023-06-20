package org.itacademy.homework9selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.steps.CheapestSteps;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
@Listeners
public class OnlinerCheapestProductComparatorStepsTest extends BaseTest {

    private CheapestSteps cheapestSteps;

    @Description("Search item in catalog and compare first 10 items")
    @Test(testName = "CheckCompare")
    public void onlinerMinPriceTest() {
        Configuration.pageLoadTimeout = 30000;
        Configuration.timeout = 30000;
        open("https://www.onliner.by/");
        getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        cheapestSteps.inputSearchValue();
        cheapestSteps.switchToFrame();
        SelenideElement minPriceElement = cheapestSteps.getCheapest();
        String cheapestTitle = minPriceElement.$(By.xpath(".//div[@class='product__title']")).getText();
        String cheapestPrice = minPriceElement.$(By.xpath(".//div[@class='product__price']//span")).getText();
        log.info("MIN PRICE ELEMENT");
        log.info("TITLE: " + cheapestTitle);
        log.info("PRICE: " + cheapestPrice);

        cheapestSteps.goToProductPage(minPriceElement);
        cheapestSteps.addToBasket();
        cheapestSteps.goToBasket();

        String basketTitle = cheapestSteps.getBasketProductTitle();
        String basketPrice = cheapestSteps.getBasketProductPrice();

        log.info("PRICE COMPARE " + cheapestPrice + " = " + basketPrice + "  " + cheapestPrice.contains(basketPrice));
        log.info("TITLE COMPARE " + cheapestTitle + " = " + basketTitle + "  " + cheapestTitle.contains(basketTitle));
        Assert.assertTrue(cheapestPrice.contains(basketPrice) && cheapestTitle.contains(basketTitle));
    }
}
