package org.itacademy.homework9selenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.utils.WaitUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
@Listeners
public class BuyNow2Test extends BaseTest {

    @Description("Search item in catalog and compare first 10 items")
    @Test(testName = "CheckCompare")
    public void onlinerMinPriceTest() {

        open("https://catalog.onliner.by/mobile/apple/iphone14128sl/prices");

        SelenideElement pop1 = $(By.xpath("//*[contains(text(), 'Все ясно')]"));
        WaitUtils.waitForVisibility(pop1, 20);
        log.info("POP1\n" + pop1.isDisplayed());
        pop1.click();

        SelenideElement pop2 = $(By.xpath("//*[contains(text(), 'Да, верно')]"));
        WaitUtils.waitForVisibility(pop2, 20);
        log.info("POP2\n" + pop1.isDisplayed());
        pop2.click();

        SelenideElement tab1 = $(By.xpath("//div[@class='offers-filter']"));
        log.info("TAB1\n" + tab1.isDisplayed());
        SelenideElement tab2 = $(By.xpath("//div[@class='offers-list__part offers-list__part_action']"));
        log.info("TAB2\n" + tab2.getText());

        $$(By.xpath("//a[contains(@href,'buyNow')]")).get(0).click();

        SelenideElement tab3 = $(By.xpath("//*[@class='button-style button-style_adscititious button-style_base-alter offers-list__button offers-list__button_buy']"));
        tab3.hover();
        log.info("TAB3\n" + tab3.isDisplayed());
        log.info("TAB3\n" + tab3.getText());
        tab3.click();

        WaitUtils.waitSeconds(10);
    }
}
