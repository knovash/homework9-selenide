package org.itacademy.homework9selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
@Listeners
public class OnlinerCheapestProductTest extends BaseTest {

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
        WaitUtils.waitForVisibility(fastSearchInput, 900);
        fastSearchInput.setValue(item);
        log.info("VALUE fastSearchInput: " + fastSearchInput.getValue());

        SelenideElement frame = $(By.xpath("//iframe[@class='modal-iframe']"));
        WaitUtils.waitForVisibility(frame, 900);
        driver.switchTo().frame(frame);

        WaitUtils.waitForVisibility($(By.xpath("//*[@class='search__result']")), 900);
        ElementsCollection searchResults = $$(By.xpath("//div[@class='result__item result__item_product']"));

        SelenideElement result1 = searchResults.get(1);
        log.info("\nTILE: " + result1.$(By.className("product__title")).getText());
//        log.info("\nRESULT html: " + result.$(By.className("product__price")).getAttribute("outerHTML"));
//        SelenideElement productPrice = result.$(By.className("product__price")); // работает
//        SelenideElement productPrice = result.$("span"); // работает
//        SelenideElement productPrice = result.$(By.tagName("span")); // работает
        SelenideElement productPrice = result1.$(By.xpath(".//span")); // работает
        log.info("\nRESULT html: " + productPrice.getAttribute("outerHTML"));
        log.info("\nRESULT html: " + productPrice.getText());


//        searchResults
//                .stream()
////                .peek(result -> log.info("\nTITLE: " + result.$(By.className("product__title")).getText()))
//                .peek(result -> log.info("\nTITLE: " + result.$(By.xpath(".//div[@class='product__title']")).getText()))
//                .peek(result -> log.info("\nPRICE: " + result.$(By.xpath(".//*[@class='product__price']//span")).getAttribute("outerHTML")))
//                .peek(result -> log.info("\nPRICE: " + result.$(By.xpath(".//*[@class='product__price']//span")).getText()))
//                .forEach(result -> log.info("---------"));

//        Double

        Comparator<Double> ccc = new Comparator<Double>() {
            @Override
            public int compare(Double doc1, Double doc2) {
                return doc1.compareTo(doc2);
            }
        };

        Comparator<SelenideElement> sel = new Comparator<SelenideElement>() {
            @Override
            public int compare(SelenideElement element1, SelenideElement element2) {
                return convert(element1).compareTo(convert(element2));
            }

            public Double convert(SelenideElement element){
                String priceText = element.$(By.xpath(".//*[@class='product__price']//span")).getText();
                priceText = priceText.replace(" р.", "");
                priceText = priceText.replace(",", ".");
                Double priceDouble = Double.valueOf(priceText);
                return priceDouble;
            }


        };


        SelenideElement minPriceElement =
        searchResults
                .stream()
                        .min(sel)
                                .get();
////

//        log.info("MIN PRICE DOUBLE: " + minPrice);

        log.info("MIN PRICE DOUBLE: " + minPriceElement.$(By.xpath(".//div[@class='product__title']")).getText());
        log.info("MIN PRICE DOUBLE: " + minPriceElement.$(By.xpath(".//*[@class='product__price']//span")).getText());



        WaitUtils.waitSeconds(5);
        log.info("--==TEST END==--");
    }
}
