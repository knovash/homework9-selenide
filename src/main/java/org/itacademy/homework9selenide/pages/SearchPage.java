package org.itacademy.homework9selenide.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage {

    private SelenideElement searchButton = $(By.xpath("//*[@class='icon icn-search']"));

    private SelenideElement searchField = $(By.xpath("//input[@class='search-header__input']"));

    private SelenideElement searchStartButton = $(By.xpath("//button[@class='search-header__btn']"));

    private final ElementsCollection resultItems = $$(By.xpath("//div[@class='col-xxs-12 col-xs-6 col-sm-4 col-md-4 col-lg-4 menuItemWrapper']//div[@class='imageData']//div[@class='h4']"));

    public SelenideElement getSearchButton() {
        return searchButton;
    }

    public SelenideElement getSearchField() {
        return searchField;
    }

    public SelenideElement getSearchStartButton() {
        return searchStartButton;
    }

    public ElementsCollection getResultItems() {
        return resultItems;
    }
}
