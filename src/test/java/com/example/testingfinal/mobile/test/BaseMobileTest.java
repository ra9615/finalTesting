package com.example.testingfinal.mobile.test;

import com.example.testingfinal.mobile.config.MobileTestConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.URL;
import java.time.Duration;

public abstract class BaseMobileTest {

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void initializeDriver() throws Exception {
        logInfo("Настройка мобильного теста");

        UiAutomator2Options capabilities = createCapabilities();
        driver = new AndroidDriver(new URL(MobileTestConfig.getAppiumServerUrl()), capabilities);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        logInfo("Appium сессия запущена");
    }

    private UiAutomator2Options createCapabilities() {
        return new UiAutomator2Options()
                .setPlatformName(MobileTestConfig.getPlatformName())
                .setAutomationName(MobileTestConfig.getAutomationName())
                .setDeviceName(MobileTestConfig.getDeviceName())
                .setPlatformVersion(MobileTestConfig.getPlatformVersion())
                .setAppPackage(MobileTestConfig.getAppPackage())
                .setAppActivity(MobileTestConfig.getAppActivity())
                .setNoReset(MobileTestConfig.isNoReset())
                .setAutoGrantPermissions(MobileTestConfig.isAutoGrantPermissions())
                .setNewCommandTimeout(Duration.ofSeconds(MobileTestConfig.getNewCommandTimeout()));
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        logInfo("Завершение теста");

        if (driver != null) {
            driver.quit();
            logInfo("Appium сессия закрыта");
        }
    }

    // Вспомогательные методы логирования
    protected void logTestStart(String testName) {
        System.out.println("[TEST START] " + testName);
    }

    protected void logStep(String step) {
        System.out.println("[STEP] " + step);
    }

    protected void logTestPass(String message) {
        System.out.println("[PASS] " + message);
    }

    protected void logInfo(String message) {
        System.out.println("[INFO] " + message);
    }
}
