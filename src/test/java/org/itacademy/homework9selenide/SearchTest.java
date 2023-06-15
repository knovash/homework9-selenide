package org.itacademy.homework9selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.itacademy.homework9selenide.models.MenuItem;
import org.itacademy.homework9selenide.steps.SearchSteps;
import org.itacademy.homework9selenide.utils.Config;
import org.itacademy.homework9selenide.utils.DataProviderSearch;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.codeborne.selenide.testng.ScreenShooter;

import static com.codeborne.selenide.Selenide.open;

@Log4j2
@Listeners
public class SearchTest extends BaseTest {

    private SearchSteps searchSteps;

    @BeforeMethod
    public void beforemethod() {
        log.info("BEFORE METHOD get page " + Config.getPageSearch());
        open(Config.getPageSearch());
        searchSteps = new SearchSteps();
        Configuration.pageLoadTimeout = 30000;
        Configuration.timeout = 30000;
    }

    @Description("Verifys search result items")
    @Issue("wrong search results")
    @Test(testName = "SearchResultsTest",
            dataProvider = "menuItems",
            dataProviderClass = DataProviderSearch.class)
    public void searchTest(MenuItem menuItem) {
        log.info("TEST SEARCH");
        searchSteps.clickSearchButton();
        searchSteps.enterSearchFieldText(menuItem.getName());
        searchSteps.clickSearchStartButton();
        searchSteps.getAssert(menuItem.getName());
    }
}
