package org.itacademy.homework9selenide.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OnlinerPage {

    public SelenideElement fastSearchInput = $(By.xpath("//input[@class='fast-search__input']"));

    public SelenideElement frame = $(By.xpath("//iframe[@class='modal-iframe']"));

    public ElementsCollection searchResults = $$(By.xpath("//div[@class='result__item result__item_product']"));

    public SelenideElement productTitle = $(By.xpath("//*[@class='catalog-masthead__title js-nav-header']"));

    public SelenideElement buttonToBasket = $(By.xpath("//*[contains(text(), 'В корзину')]"));

    public SelenideElement buttonGoToBasket = $(By.xpath("//*[contains(text(), 'Перейти в корзину')]"));

    public ElementsCollection inBasketItems = $$(By.xpath("//div[@class='cart-form__offers-unit cart-form__offers-unit_primary']"));

}