package org.itacademy.homework9selenide.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class Element {

   private SelenideElement minPriceElement;

    public Element(SelenideElement minPriceElement) {
        this.minPriceElement = minPriceElement;
    }

    public SelenideElement ElementTitle = minPriceElement.$(By.xpath(".//*[@class='product__title-link']"));

}
