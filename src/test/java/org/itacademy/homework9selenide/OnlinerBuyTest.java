package org.itacademy.homework9selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class OnlinerBuyTest extends BaseTest {

    @Description("Search item in catalog and compare first 10 items")
    @Test(testName = "OnlinerBuyTest")
    public void onlnerCompareTest() {
        log.info("OPEN PAGE https://catalog.onliner.by/mobile/apple/iphone14");
        open("https://catalog.onliner.by/mobile/apple/iphone14");
        WebDriver driver = getWebDriver();
        driver.manage().timeouts().pageLoadTimeout(600L, TimeUnit.SECONDS);

        SelenideElement title = $(By.xpath("//*[@class='catalog-masthead__title js-nav-header']"));
        WaitUtils.waitForVisibility(title, 600);

        title.shouldBe(visible);
        log.info("DISPLAYED title: " + title.isDisplayed());
        log.info("HTML title: " + title.getAttribute("outerHTML"));
        log.info("TEXT title: " + title.text());

        SelenideElement buttonBuyNow = $(By.xpath("//*[@class='button-style button-style_adscititious button-style_base-alter product-aside__button product-aside__button_narrow product-aside__button_buy']"));
        WaitUtils.waitForVisibility(buttonBuyNow, 900);
        log.info("DISPLAYED buttonBuyNow: " + buttonBuyNow.isDisplayed());
        log.info("TEXT buttonBuyNow: " + buttonBuyNow.text());
        buttonBuyNow.click();

        SelenideElement inputCity = $(By.xpath("//input[@placeholder='Укажите населенный пункт']"));
        WaitUtils.waitForVisibility(inputCity, 900);
        log.info("TEXT inputCity: " + inputCity.getValue());
        // кнопка очистить ненажимается
        SelenideElement clearCity = $(By.xpath("//input[@placeholder='Укажите населенный пункт']/following-sibling::div"));
        log.info("DISP clearCity: " + clearCity.isDisplayed());
//        inputCity.clear(); // очистка поля неработает
//        clearCity.click(); // кнопка очистить ненажимается
//        inputCity.sendKeys(Keys.BACK_SPACE); // вариант удаления бэкспэйсом
        inputCity.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        inputCity.setValue("Брест");
        inputCity.shouldHave(value("Брест"));
        log.info("TEXT inputCity: " + inputCity.getValue());

        SelenideElement inputStreet = $(By.xpath("//*[contains(text(), 'Адрес доставки')]/following-sibling::div/following-sibling::div//input"));
        inputStreet.setValue("Кульман");
        inputStreet.shouldHave(value("Кульман"));
        log.info("TEXT inputStreet: " + inputStreet.getValue());

        SelenideElement inputBuilding = $(By.xpath("//div[@class='cart-form__label-title' and contains(text(), 'Дом')]/../../../following-sibling::div//input"));
        inputBuilding.setValue("12");
        inputBuilding.shouldHave(value("12"));
        log.info("TEXT inputBuilding: " + inputBuilding.getValue());

        SelenideElement comment = $(By.xpath("//*[@placeholder='Укажите дополнительные детали']"));
        comment.setValue("Shut up and take my money!");
        comment.shouldHave(value("Shut up and take my money!"));
        log.info("TEXT inputBuilding: " + comment.getValue());

        SelenideElement firstName = $(By.xpath("//*[contains(text(), 'Имя')]/../../../following-sibling::*/div/input"));
        firstName.setValue("Konstantin");
        firstName.shouldHave(value("Konstantin"));
        log.info("TEXT firstName: " + firstName.getValue());

        SelenideElement lastName = $(By.xpath("//*[contains(text(), 'Фамилия')]/../../../following-sibling::*/div/input"));
        lastName.setValue("Novash");
        lastName.shouldHave(value("Novash"));
        log.info("TEXT lastName: " + lastName.getValue());

        SelenideElement email = $(By.xpath("//*[contains(text(), 'E-mail')]/../../../following-sibling::*/div/input"));
        email.setValue("novash@gmail.com");
        email.shouldHave(value("novash@gmail.com"));
        log.info("TEXT lastName: " + email.getValue());

        log.info("--==TEST END==--");
    }
}
