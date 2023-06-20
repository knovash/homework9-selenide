package org.itacademy.homework9selenide.utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class PriceUtils {

    public static Double getPriceDouble(SelenideElement element) {
        String priceText = element.$(By.xpath(".//*[@class='product__price']//span")).getText();
        priceText = priceText
                .replace(" р.", "")
                .replace(" ", "")
                .replace(",", ".");
        return Double.valueOf(priceText);
    }
}
