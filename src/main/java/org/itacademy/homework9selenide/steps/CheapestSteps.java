package org.itacademy.homework9selenide.steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.pages.OnlinerPage;
import org.itacademy.homework9selenide.utils.WaitUtils;
import org.openqa.selenium.By;

import java.util.Comparator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class CheapestSteps {

    private OnlinerPage onlinerPage;

    public CheapestSteps() {
        onlinerPage = new OnlinerPage();
    }

    @Step("input Search Value")
    public void inputSearchValue() {
        log.info("input Search Value = iphone 12");
        WaitUtils.waitForVisibility(onlinerPage.fastSearchInput, 60);
        onlinerPage.fastSearchInput.setValue("iphone 12");
    }

    @Step("switch To Frame")
    public void switchToFrame() {
        log.info("switch To Frame");
        SelenideElement frame = $(By.xpath("//iframe[@class='modal-iframe']"));
        WaitUtils.waitForVisibility(frame, 60);
        getWebDriver().switchTo().frame(frame);
    }

    @Step("get Cheapest Product Element")
    public SelenideElement getCheapestProductElement() {

        Comparator<SelenideElement> priceComparator = new Comparator<SelenideElement>() {
            @Override
            public int compare(SelenideElement element1, SelenideElement element2) {
                return getPriceDouble(element1).compareTo(getPriceDouble(element2));
            }
        };

        log.info("get Cheapest Product Element");
        WaitUtils.waitForVisibility(onlinerPage.searchResults.get(0), 60);
        log.info("SIZE " + onlinerPage.searchResults.size());
        return onlinerPage.searchResults
                .stream()
                .sorted(priceComparator)
                .peek(element -> log.info(getPriceDouble(element)))
                .min(priceComparator)
                .get();
    }

    public Double getPriceDouble(SelenideElement element) {
        String priceText = element.$(By.xpath(".//*[@class='product__price']//span")).getText();
        priceText = priceText
                .replace(" р.", "")
                .replace(" ", "")
                .replace(",", ".");
        return Double.valueOf(priceText);
    }

    @Step("get Cheapest Title Text")
    public String getCheapestTitleText(SelenideElement minPriceElement) {
        String cheapestTitle = minPriceElement.$(By.xpath(".//div[@class='product__title']")).getText();
        log.info("get Cheapest Title Text: " + cheapestTitle);
        return cheapestTitle;
    }

    @Step("get Cheapest Price Text")
    public String getCheapestPriceText(SelenideElement minPriceElement) {
        String cheapestPrice = minPriceElement.$(By.xpath(".//div[@class='product__price']//span")).getText();
        log.info("get Cheapest Price Text: " + cheapestPrice);
        return cheapestPrice;
    }

    @Step("go To Product Page")
    public void goToProductPage(SelenideElement minPriceElement) {
        log.info("go To Product Page");
        SelenideElement title = minPriceElement.$(By.xpath(".//*[@class='product__title-link']"));
        WaitUtils.waitForVisibility(title, 900);
        title.click();
        SelenideElement productTitle = onlinerPage.productTitle;
        WaitUtils.waitForVisibility(productTitle, 60);
        log.info("product Page Title Text: " + productTitle.getText());
    }

    @Step("add Product To Basket")
    public void addProductToBasket() {
        SelenideElement tobasket = onlinerPage.tobasket;
        WaitUtils.waitForVisibility(tobasket, 60);
        log.info("add Product To Basket: " + tobasket.isDisplayed());
        tobasket.click();
    }

    @Step("go To Basket Page")
    public void goToBasketPage() {
        SelenideElement goToBasket = onlinerPage.goToBasket;
        WaitUtils.waitForVisibility(goToBasket, 60);
        log.info("go To Basket Page: " + goToBasket.isDisplayed());
        goToBasket.click();
    }

    @Step("get Basket Product Title")
    public String getBasketProductTitle() {
        return onlinerPage.inBasketItems.get(0).$(By.xpath(".//a[contains(@class,'cart-form__link_base-alter')]")).getText();
    }

    @Step("get Basket Product Price")
    public String getBasketProductPrice() {
        return onlinerPage.inBasketItems.get(0).$(By.xpath(".//div[contains(@class,'cart-form__offers-part_price_specific')]")).getText();
    }
}
