package com.example.testingfinal.web.test;

import com.example.testingfinal.web.page.SmartLabMobilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmartLabMobileUiElementsTest  extends BaseWebTest{
    @Test
    public void shouldDisplayMainUiElementsOnSmartLabMobilePage() {

        System.out.println("[INFO] Starting test: SmartLabMobileUiElementsTest");

        SmartLabMobilePage page = new SmartLabMobilePage(driver);

        // 1. Открываем главную страницу
        System.out.println("[STEP] Open Smart-Lab Mobile main page");
        page.openPage(baseUrl);

        // 2. Проверяем наличие основных финансовых секций
        System.out.println("[CHECK] Verify 'Leaders' section is displayed");
        Assert.assertTrue(
                page.isLeadersSectionDisplayed(),
                "Секция 'Лидеры роста и падения' не отображается"
        );

        System.out.println("[CHECK] Verify 'Indices' section is displayed");
        Assert.assertTrue(
                page.isIndicesSectionDisplayed(),
                "Секция 'Индексы' не отображается"
        );

        System.out.println("[CHECK] Verify 'FOREX' section is displayed");
        Assert.assertTrue(
                page.isForexSectionDisplayed(),
                "Секция 'FOREX' не отображается"
        );

        System.out.println("[CHECK] Verify 'Cryptocurrencies' section is displayed");
        Assert.assertTrue(
                page.isCryptocurrenciesSectionDisplayed(),
                "Секция 'Криптовалюты' не отображается"
        );

        // 4. Проверяем формат изменений цен
        System.out.println("[CHECK] Verify quote changes are correctly formatted");
        Assert.assertTrue(
                page.areQuoteChangesFormattedCorrectly(),
                "Изменения цен отформатированы некорректно (должны содержать %)"
        );

        String currentDate = page.getCurrentDate();
        System.out.println("[INFO] Current date on page: " + currentDate);

        // 6. Проверяем элементы форума
        System.out.println("[CHECK] Verify forum posts are present");
        int forumPostsCount = page.getForumPostTitlesCount();
        System.out.println("[INFO] Found " + forumPostsCount + " forum post titles");

        Assert.assertTrue(
                forumPostsCount >= 3,
                "Должно быть не менее 3 заголовков форумных постов. Найдено: " + forumPostsCount
        );

        // 7. Проверяем ссылку на старый дизайн
        System.out.println("[CHECK] Verify 'Old Design' link is displayed");
        Assert.assertTrue(
                page.isOldDesignLinkDisplayed(),
                "Ссылка 'Старый дизайн' не отображается"
        );

        System.out.println("[INFO] SmartLabMobileUiElementsTest finished successfully");
    }
}
