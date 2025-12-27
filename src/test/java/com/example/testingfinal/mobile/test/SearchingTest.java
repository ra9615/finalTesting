package com.example.testingfinal.mobile.test;

import com.example.testingfinal.mobile.page.MainPage;
import com.example.testingfinal.mobile.page.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchingTest extends BaseMobileTest {

    @Test(description = "Проверка поиска статьи по ключевому слову")
    public void verifyArticleSearch() {
        logTestStart("SearchTest");

        MainPage mainPage = new MainPage(driver, wait);
        SearchPage searchPage = new SearchPage(driver);

        logStep("Пропуск онбординга");
        mainPage.skipOnboarding();

        logStep("Открытие экрана поиска");
        mainPage.navigateToSearch();

        logStep("Ввод поискового запроса 'Java'");
        searchPage.searchFor("Java");

        logStep("Проверка результатов поиска");
        Assert.assertTrue(
                searchPage.hasSearchResults(),
                "Результаты поиска отсутствуют"
        );

        logTestPass("Результаты поиска отображены");
    }
}
