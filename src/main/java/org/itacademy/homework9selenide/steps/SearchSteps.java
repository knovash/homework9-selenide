package org.itacademy.homework9selenide.steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.itacademy.homework9selenide.pages.SearchPage;

import org.testng.asserts.SoftAssert;

@Log4j2
public class SearchSteps {

    private SearchPage searchPage;

    public SearchSteps() {
        searchPage = new SearchPage();
    }

    @Step("Click search button for show search field")
    public void clickSearchButton() {
        log.info("Click search button for show search field");
        searchPage.getSearchButton().click();
    }

    @Step("Enter text in search field")
    public void enterSearchFieldText(String text) {
        log.info("Enter text in search field");
        searchPage.getSearchField().sendKeys(text);
    }

    @Step("Click start search button")
    public void clickSearchStartButton() {
        log.info("Click start search button");
        searchPage.getSearchStartButton().click();
    }

    @Step("Verify result list of menu items")
    public SoftAssert getAssert(String menuItem) {
        log.info("Verify result list of menu items");
        SoftAssert sa = new SoftAssert();
        sa.assertFalse(searchPage.getResultItems().isEmpty(), "RESULT LIST IS EMPTY");
        searchPage.getResultItems().stream()
                .map(webElement -> webElement.getText().toLowerCase())
                .peek(text -> log.info("ITEM TEXT: " + text))
                .forEach(text -> sa.assertTrue(text.contains(menuItem), text + " IT'S NOT A " + menuItem));
        sa.assertAll();
        return sa;
    }
}
