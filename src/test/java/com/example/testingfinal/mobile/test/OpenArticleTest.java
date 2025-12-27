package com.example.testingfinal.mobile.test;

import com.example.testingfinal.mobile.page.ArticlePage;
import com.example.testingfinal.mobile.page.MainPage;
import com.example.testingfinal.mobile.page.SearchPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class OpenArticleTest extends BaseMobileTest{
    @Test(description = "Проверка открытия статьи из поиска")
    public void verifyArticleOpensFromSearch() {
        logTestStart("OpenArticleTest");

        MainPage mainPage = new MainPage(driver, wait);
        SearchPage searchPage = new SearchPage(driver);
        ArticlePage articlePage = new ArticlePage(driver, wait);

        logStep("Пропуск онбординга");
        mainPage.skipOnboarding();

        logStep("Переход к поиску");
        mainPage.navigateToSearch();

        logStep("Поиск статьи 'Java'");
        searchPage.searchFor("Java");

        logStep("Открытие первого результата");
        searchPage.openFirstSearchResult();

        logStep("Закрытие всплывающих окон");
        articlePage.closePopups();

        logStep("Проверка открытия статьи");
        assertTrue(articlePage.isArticleOpen(), "Статья не открылась");

        logTestPass("Статья успешно открыта");
    }
}
