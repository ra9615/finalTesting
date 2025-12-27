package com.example.testingfinal.web.test;

import com.example.testingfinal.web.page.SmartLabMobilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenSmartLabMobilePageTest extends BaseWebTest{

    @Test
    public void shouldOpenSmartLabMobileMainPage() {

        System.out.println("[INFO] Starting test: OpenSmartLabMobilePageTest");

        SmartLabMobilePage smartLabPage = new SmartLabMobilePage(driver);

        // 1. Открываем мобильную версию Smart-Lab
        System.out.println("[STEP] Open Smart-Lab Mobile main page");
        smartLabPage.openPage(baseUrl);

        // 2. Проверяем заголовок страницы
        System.out.println("[CHECK] Verify page title");
        String pageTitle = smartLabPage.getPageTitle();
        System.out.println("[INFO] Page title: " + pageTitle);

        // Smart-Lab может не иметь специфичного заголовка, проверяем базово
        Assert.assertTrue(
                pageTitle.length() > 5,
                "Заголовок страницы слишком короткий или отсутствует"
        );

        // 3. Проверяем URL
        System.out.println("[CHECK] Verify current URL");
        String currentUrl = smartLabPage.getCurrentUrl();
        System.out.println("[INFO] Current URL: " + currentUrl);

        Assert.assertTrue(
                currentUrl.contains("smart-lab.ru/mobile"),
                "URL не соответствует мобильной версии Smart-Lab"
        );

        System.out.println("[INFO] OpenSmartLabMobilePageTest finished successfully");
    }
}
