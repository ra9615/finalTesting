package com.example.testingfinal.mobile.test;

import com.example.testingfinal.mobile.page.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpenAppTest extends BaseMobileTest {

    @Test(description = "Проверка успешного открытия главного экрана")
    public void verifyMainScreenOpens() {
        logTestStart("AppLaunchTest");

        MainPage mainPage = new MainPage(driver, wait);

        logStep("Пропуск онбординга");
        mainPage.skipOnboarding();

        logStep("Проверка главного экрана");
        Assert.assertTrue(
                mainPage.isMainScreenDisplayed(),
                "Главный экран не отобразился"
        );
        logTestPass("Главный экран успешно открыт");
    }
}
