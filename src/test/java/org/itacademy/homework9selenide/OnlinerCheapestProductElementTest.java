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

import java.time.Duration;
import java.util.Comparator;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
@Listeners
public class OnlinerCheapestProductElementTest extends BaseTest {

    @Description("Search item in catalog and compare first 10 items")
    @Test(testName = "CheckCompare")
    public void onlinerMinPriceTest() {
        log.info("OPEN PAGE https://www.onliner.by/");
        open("https://www.onliner.by/");
        WebDriver driver = getWebDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1000));

        SelenideElement fastSearchInput = $(By.xpath("//input[@class='fast-search__input']"));
        WaitUtils.waitForVisibility(fastSearchInput, 900);
        fastSearchInput.setValue("iphone 12");
        log.info("VALUE fastSearchInput: " + fastSearchInput.getValue());

        SelenideElement frame = $(By.xpath("//iframe[@class='modal-iframe']"));
        WaitUtils.waitForVisibility(frame, 900);
        driver.switchTo().frame(frame);

        WaitUtils.waitForVisibility($(By.xpath("//*[@class='search__result']")), 900);
        ElementsCollection searchResults = $$(By.xpath("//div[@class='result__item result__item_product']"));

        Comparator<SelenideElement> priceComparator = new Comparator<SelenideElement>() {
            @Override
            public int compare(SelenideElement element1, SelenideElement element2) {
                return getPrice(element1).compareTo(getPrice(element2));
            }
        };

        SelenideElement minPriceElement = searchResults
                .stream()
                .peek(element -> log.info("PRICE: " + getPrice(element)))
                .min(priceComparator)
                .get();

        log.info("MIN PRICE ELEMENT");
        log.info("TITLE: " + minPriceElement.$(By.xpath(".//div[@class='product__title']")).getText());
        log.info("PRICE: " + minPriceElement.$(By.xpath(".//div[@class='product__price']//span")).getText());
        WaitUtils.waitSeconds(5);
        log.info("--==TEST END==--");
    }

    public Double getPrice(SelenideElement element) {
        String priceText = element.$(By.xpath(".//*[@class='product__price']//span")).getText();
        priceText = priceText.replace(" р.", "");
        priceText = priceText.replace(" ", "");
        priceText = priceText.replace(",", ".");
        return Double.valueOf(priceText);
    }
}
