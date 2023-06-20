package org.itacademy.homework9selenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
@Listeners
public class BuyNow1Test extends BaseTest {

    @Description("Search item in catalog and compare first 10 items")
    @Test(testName = "CheckCompare")
    public void onlinerMinPriceTest() {
        open("https://catalog.onliner.by/mobile/apple/iphone14");
//        $$(By.xpath("//a[contains(@href,'buyNow')]")).get(0).click();
        SelenideElement tab3 = $(By.xpath("//*[@class='button-style button-style_adscititious button-style_base-alter product-aside__button product-aside__button_narrow product-aside__button_buy']"));
        tab3.hover();
        log.info("TAB3\n" + tab3.isDisplayed());
        log.info("TAB3\n" + tab3.getText());
        tab3.click();
        WaitUtils.waitSeconds(10);
    }
}
