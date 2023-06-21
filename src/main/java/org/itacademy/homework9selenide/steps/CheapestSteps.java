package org.itacademy.homework9selenide.steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.pages.OnlinerPage;
import org.itacademy.homework9selenide.utils.PriceUtils;
import org.itacademy.homework9selenide.utils.WaitUtils;

import java.util.Comparator;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class CheapestSteps {

    OnlinerPage onlinerPage = new OnlinerPage();

    @Step("input Search Value")
    public void inputSearchValue() {
        log.info("input Search Value = iphone 12");
        WaitUtils.waitForVisibility(onlinerPage.fastSearchInput, 60);
        onlinerPage.fastSearchInput.setValue("iphone 12");
    }

    @Step("switch To Frame")
    public void switchToFrame() {
        log.info("switch To Frame");
        SelenideElement frame = onlinerPage.frame;
        WaitUtils.waitForVisibility(frame, 60);
        getWebDriver().switchTo().frame(frame);
    }

    @Step("get Cheapest Product Element")
    public SelenideElement getCheapestProductElement() {

        Comparator<SelenideElement> priceComparator = new Comparator<SelenideElement>() {
            @Override
            public int compare(SelenideElement element1, SelenideElement element2) {
                return PriceUtils.getPriceDouble(element1).compareTo(PriceUtils.getPriceDouble(element2));
            }
        };

        log.info("get Cheapest Product Element");
        WaitUtils.waitForVisibility(onlinerPage.searchResults.get(0), 60);
        log.info("SIZE " + onlinerPage.searchResults.size());
        SelenideElement result = onlinerPage.searchResults
                .stream()
                .peek(element -> log.info(PriceUtils.getPriceDouble(element)))
                .min(priceComparator)
                .get();
        return result;
    }

    @Step("get Cheapest Title Text")
    public String getCheapestTitleText(SelenideElement minPriceElement) {
//        String cheapestTitle = minPriceElement.$(By.xpath(".//div[@class='product__title']")).getText();
        String cheapestTitle = onlinerPage.getElementTitle(minPriceElement).getText();
        log.info("get Cheapest Title Text: " + cheapestTitle);
        return cheapestTitle;
    }

    @Step("get Cheapest Price Text")
    public String getCheapestPriceText(SelenideElement minPriceElement) {
//        String cheapestPrice = minPriceElement.$(By.xpath(".//div[@class='product__price']//span")).getText();
        String cheapestPrice = onlinerPage.getElementPrice(minPriceElement).getText();
        log.info("get Cheapest Price Text: " + cheapestPrice);
        return cheapestPrice;
    }

    @Step("go To Product Page")
    public void goToProductPage(SelenideElement minPriceElement) {
        log.info("go To Product Page");
//        SelenideElement link = minPriceElement.$(By.xpath(".//*[@class='product__title-link']"));
        SelenideElement link = onlinerPage.getProductLink(minPriceElement);
        WaitUtils.waitForVisibility(link, 60);
        link.click();
        log.info("wait for product page title...");
        SelenideElement productTitle = onlinerPage.productTitle;
        WaitUtils.waitForVisibility(productTitle, 60);
        log.info("product Page Title Text: " + productTitle.getText());
    }

    @Step("add Product To Basket")
    public void addProductToBasket() {
        SelenideElement addToBasket = onlinerPage.buttonToBasket;
        WaitUtils.waitForVisibility(addToBasket, 60);
        log.info("add Product To Basket: " + addToBasket.isDisplayed());
        addToBasket.click();
    }

    @Step("go To Basket Page")
    public void goToBasketPage() {
        SelenideElement goToBasket = onlinerPage.buttonGoToBasket;
        WaitUtils.waitForVisibility(goToBasket, 60);
        log.info("go To Basket Page: " + goToBasket.isDisplayed());
        goToBasket.click();
    }

    @Step("get Basket Product Title")
    public String getBasketProductTitle() {
//        return onlinerPage.inBasketItems.get(0).$(By.xpath(".//a[contains(@class,'cart-form__link_base-alter')]")).getText();
        return onlinerPage.getElementInBasketTitle().getText();
    }

    @Step("get Basket Product Price")
    public String getBasketProductPrice() {
//        return onlinerPage.inBasketItems.get(0).$(By.xpath(".//div[contains(@class,'cart-form__offers-part_price_specific')]")).getText();
        return onlinerPage.getElementInBasketPrice().getText();
    }
}
