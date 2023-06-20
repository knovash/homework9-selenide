package org.itacademy.homework9selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Comparator;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
@Listeners
public class OnlinerCheapestProductComparatorTest extends BaseTest {

    @Description("Search item in catalog and compare first 10 items")
    @Test(testName = "CheckCompare")
    public void onlinerMinPriceTest() {
        Configuration.pageLoadTimeout = 30000;
        Configuration.timeout = 30000;

        log.info("OPEN PAGE https://www.onliner.by/");
        open("https://www.onliner.by/");
        WebDriver driver = getWebDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        SelenideElement fastSearchInput = $(By.xpath("//input[@class='fast-search__input']"));
        WaitUtils.waitForVisibility(fastSearchInput, 60);
        fastSearchInput.setValue("iphone 12");
        log.info("VALUE fastSearchInput: " + fastSearchInput.getValue());

        SelenideElement frame = $(By.xpath("//iframe[@class='modal-iframe']"));
        WaitUtils.waitForVisibility(frame, 60);
        driver.switchTo().frame(frame);
        WaitUtils.waitForVisibility($(By.xpath("//*[@class='search__result']")), 60);

        Comparator<SelenideElement> priceComparator = new Comparator<SelenideElement>() {
            @Override
            public int compare(SelenideElement element1, SelenideElement element2) {
                return getPriceDouble(element1).compareTo(getPriceDouble(element2));
            }
        };

        ElementsCollection searchResults = $$(By.xpath("//div[@class='result__item result__item_product']"));
        SelenideElement minPriceElement = searchResults
                .stream()
                .sorted(priceComparator)
                .peek(element -> log.info(getPriceDouble(element)))
                .min(priceComparator)
                .get();

        String cheapestTitle = minPriceElement.$(By.xpath(".//div[@class='product__title']")).getText();
        String cheapestPrice = minPriceElement.$(By.xpath(".//div[@class='product__price']//span")).getText();
        log.info("MIN PRICE ELEMENT");
        log.info("TITLE: " + cheapestTitle);
        log.info("PRICE: " + cheapestPrice);

        SelenideElement title = minPriceElement.$(By.xpath(".//*[@class='product__title-link']"));
        WaitUtils.waitForVisibility(title, 900);
        log.info("TITLE CLICK");
        title.click();

        SelenideElement productTitle = $(By.xpath(".//*[@class='catalog-masthead__title js-nav-header']"));
        WaitUtils.waitForVisibility(productTitle, 60);
        log.info("TITLE TEXT " + productTitle.getText());

        SelenideElement tobasket = $(By.xpath("//*[contains(text(), 'В корзину')]"));
        WaitUtils.waitForVisibility(tobasket, 60);
        log.info("CLICK В корзину\n" + tobasket.isDisplayed());
        tobasket.click();

        SelenideElement goToBasket = $(By.xpath("//*[contains(text(), 'Перейти в корзину')]"));
        WaitUtils.waitForVisibility(tobasket, 60);
        log.info("CLICK Перейти в корзину\n" + goToBasket.isDisplayed());
        goToBasket.click();

        ElementsCollection inBasketItems = $$(By.xpath("//div[@class='cart-form__offers-unit cart-form__offers-unit_primary']"));
        SelenideElement product1 = inBasketItems.get(0);
        String basketTitle = product1.$(By.xpath(".//a[contains(@class,'cart-form__link_base-alter')]")).getText();
        String basketPrice = product1.$(By.xpath(".//div[contains(@class,'cart-form__offers-part_price_specific')]")).getText();

        log.info("PRICE " + cheapestPrice + " = " + basketPrice + "  " +  cheapestPrice.contains(basketPrice));
        log.info("TITLE " + cheapestTitle + " = " + basketTitle + "  " + cheapestTitle.contains(basketTitle));

        Assert.assertTrue(cheapestPrice.contains(basketPrice) && cheapestTitle.contains(basketTitle));

        WaitUtils.waitSeconds(20);
        log.info("--==TEST END==--");
    }

    public Double getPriceDouble(SelenideElement element) {
        String priceText = element.$(By.xpath(".//*[@class='product__price']//span")).getText();
        priceText = priceText
                .replace(" р.", "")
                .replace(" ", "")
                .replace(",", ".");
        return Double.valueOf(priceText);
    }
}
