package org.itacademy.homework9selenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.pages.OnlinerPage;
import org.itacademy.homework9selenide.steps.CheapestSteps;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
@Listeners
public class OnlinerCheapestProductTest extends BaseTest {

    CheapestSteps cheapestSteps = new CheapestSteps();

    @Description("Find cheapest product and add to basket")
    @Test()
    public void cheapestProductTest() {

        log.info("OPEN https://www.onliner.by/");
        open("https://www.onliner.by/");
        getWebDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));



        cheapestSteps.inputSearchValue();
        cheapestSteps.switchToFrame();
        SelenideElement cheapestProductElement = cheapestSteps.getCheapestProductElement();
//        OnlinerPage.cheapestElement = cheapestSteps.getCheapestProductElement();

        String cheapestTitle = cheapestSteps.getCheapestTitleText(cheapestProductElement);
        String cheapestPrice = cheapestSteps.getCheapestPriceText(cheapestProductElement);
        cheapestSteps.goToProductPage(cheapestProductElement);
        cheapestSteps.addProductToBasket();
        cheapestSteps.goToBasketPage();
        String basketTitle = cheapestSteps.getBasketProductTitle();
        String basketPrice = cheapestSteps.getBasketProductPrice();
        log.info("PRICE COMPARE " + cheapestPrice + " = " + basketPrice + "  " + cheapestPrice.contains(basketPrice));
        log.info("TITLE COMPARE " + cheapestTitle + " = " + basketTitle + "  " + cheapestTitle.contains(basketTitle));
        Assert.assertTrue(cheapestPrice.contains(basketPrice) && cheapestTitle.contains(basketTitle));
    }
}
