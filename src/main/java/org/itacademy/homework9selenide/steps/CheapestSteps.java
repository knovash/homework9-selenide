package org.itacademy.homework9selenide.steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.pages.OnlinerPage;
import org.itacademy.homework9selenide.utils.WaitUtils;
import org.openqa.selenium.By;

import java.util.Comparator;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class CheapestSteps {

    private OnlinerPage onlinerPage;

    public CheapestSteps() {
        onlinerPage = new OnlinerPage();
    }

    @Step("Click search button for show search field")
    public void inputSearchValue() {
        log.info("input Search Value = iphone 12");
        WaitUtils.waitForVisibility(onlinerPage.fastSearchInput, 60);
        onlinerPage.fastSearchInput.setValue("iphone 12");
        log.info("VALUE fastSearchInput: " + onlinerPage.fastSearchInput.getValue());
    }

    @Step("Click search button for show search field")
    public void switchToFrame() {
        log.info("switch To Frame");
        SelenideElement frame = onlinerPage.frame;
        getWebDriver().switchTo().frame(frame);
        WaitUtils.waitForVisibility(frame, 60);
    }

    @Step("Click search button for show search field")
    public SelenideElement getCheapest() {
        log.info("getCheapest");

        Comparator<SelenideElement> priceComparator = new Comparator<SelenideElement>() {
            @Override
            public int compare(SelenideElement element1, SelenideElement element2) {
                return getPriceDouble(element1).compareTo(getPriceDouble(element2));
            }
        };

        SelenideElement minPriceElement = onlinerPage.searchResults
                .stream()
                .sorted(priceComparator)
                .peek(element -> log.info(getPriceDouble(element)))
                .min(priceComparator)
                .get();

        log.info("switch ok");
        return minPriceElement;
    }

    public Double getPriceDouble(SelenideElement element) {
        String priceText = element.$(By.xpath(".//*[@class='product__price']//span")).getText();
        priceText = priceText
                .replace(" р.", "")
                .replace(" ", "")
                .replace(",", ".");
        return Double.valueOf(priceText);
    }

    @Step("Click search button for show search field")
    public void goToProductPage(SelenideElement minPriceElement) {
        log.info("goToProductPage");
        SelenideElement title = minPriceElement.$(By.xpath(".//*[@class='product__title-link']"));
        WaitUtils.waitForVisibility(title, 900);
        title.click();
        SelenideElement productTitle = onlinerPage.productTitle;
        WaitUtils.waitForVisibility(productTitle, 60);
        log.info("TITLE TEXT " + productTitle.getText());
    }

    @Step("Click search button for show search field")
    public void addToBasket() {
        SelenideElement tobasket = onlinerPage.tobasket;
        WaitUtils.waitForVisibility(tobasket, 60);
        log.info("CLICK В корзину\n" + tobasket.isDisplayed());
        tobasket.click();
    }

    @Step("Click search button for show search field")
    public void goToBasket() {
        SelenideElement goToBasket = onlinerPage.goToBasket;
        WaitUtils.waitForVisibility(goToBasket, 60);
        log.info("CLICK Перейти в корзину\n" + goToBasket.isDisplayed());
        goToBasket.click();
    }

    @Step("Click search button for show search field")
    public String getBasketProductTitle() {
        return onlinerPage.inBasketItems.get(0).$(By.xpath(".//a[contains(@class,'cart-form__link_base-alter')]")).getText();
    }

    @Step("Click search button for show search field")
    public String getBasketProductPrice() {
        return onlinerPage.inBasketItems.get(0).$(By.xpath(".//div[contains(@class,'cart-form__offers-part_price_specific')]")).getText();
    }
}
