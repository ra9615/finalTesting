package com.example.testingfinal.mobile.test;

import com.example.testingfinal.mobile.page.ArticlePage;
import com.example.testingfinal.mobile.page.MainPage;
import com.example.testingfinal.mobile.page.SearchPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;

public class ScrollingTest extends BaseMobileTest{
    @Test(description = "Проверка прокрутки статьи")
    public void verifyArticleScrolling() {
        logTestStart("ScrollArticleTest");

        MainPage mainPage = new MainPage(driver, wait);
        SearchPage searchPage = new SearchPage(driver);
        ArticlePage articlePage = new ArticlePage(driver, wait);

        logStep("Пропуск онбординга");
        mainPage.skipOnboarding();

        logStep("Открытие поиска");
        mainPage.navigateToSearch();

        logStep("Поиск 'Java'");
        searchPage.searchFor("Java");

        logStep("Открытие первого результата");
        searchPage.openFirstSearchResult();

        logStep("Закрытие всплывающих окон");
        articlePage.closePopups();

        logStep("Сохранение состояния до прокрутки");
        String contentBeforeScroll = driver.getPageSource();

        logStep("Выполнение прокрутки");
        articlePage.scrollDown();

        logStep("Сохранение состояния после прокрутки");
        String contentAfterScroll = driver.getPageSource();

        logStep("Проверка изменения контента");
        assertNotEquals(contentBeforeScroll, contentAfterScroll,
                "Контент не изменился после прокрутки");

        logTestPass("Статья успешно прокручена");
    }
}
