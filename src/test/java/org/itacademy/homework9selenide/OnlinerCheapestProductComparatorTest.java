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
public class OnlinerCheapestProductComparatorTest extends BaseTest {

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

        Comparator<SelenideElement> priceComparator = new Comparator<SelenideElement>() {
            @Override
            public int compare(SelenideElement element1, SelenideElement element2) {
                return getPriceDouble(element1).compareTo(getPriceDouble(element2));
            }
        };

        ElementsCollection searchResults = $$(By.xpath("//div[@class='result__item result__item_product']"));
        SelenideElement minPriceElement = searchResults
                .stream()
                .min(priceComparator)
                .get();

        log.info("MIN PRICE ELEMENT");
        log.info("TITLE: " + minPriceElement.$(By.xpath(".//div[@class='product__title']")).getText());
        log.info("PRICE: " + minPriceElement.$(By.xpath(".//div[@class='product__price']//span")).getText());

        SelenideElement buttonOrange = minPriceElement.$(By.xpath(".//*[@class='button button_orange product__button']"));
        WaitUtils.waitForVisibility(buttonOrange, 900);
        buttonOrange.click();

        WaitUtils.waitSeconds(5);
        SelenideElement popover = $(By.xpath("//*[contains(text(), 'Все ясно, спасибо')]"));
        WaitUtils.waitForVisibility(popover, 30);
        if (popover.isDisplayed()) {
            popover.click();
        }

        WaitUtils.waitSeconds(5);
        SelenideElement popover2 = $(By.xpath("//*[contains(text(), 'Ваш населенный пункт')]"));
        if (popover2.isDisplayed()) {
            log.info("Ваш населенный пункт");
            $(By.xpath("//*[contains(text(), 'Ваш населенный пункт')]")).click();
        }

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
