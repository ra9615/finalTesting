package com.example.testingfinal.mobile.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final By SEARCH_CONTAINER = By.id("org.wikipedia.alpha:id/search_container");
    private final By SKIP_BUTTON = By.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button");

    public MainPage(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void skipOnboarding() {
        if (isElementVisible(SKIP_BUTTON)) {
            driver.findElement(SKIP_BUTTON).click();
            System.out.println("[INFO] Онбординг пропущен");
        }
    }

    public boolean isMainScreenDisplayed() {
        boolean isDisplayed = wait.until(
                ExpectedConditions.visibilityOfElementLocated(SEARCH_CONTAINER)
        ).isDisplayed();

        System.out.println("[INFO] Главный экран " + (isDisplayed ? "открыт" : "не открыт"));
        return isDisplayed;
    }

    public void navigateToSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(SEARCH_CONTAINER)).click();
        System.out.println("[INFO] Переход на экран поиска");
    }

    private boolean isElementVisible(By locator) {
        return !driver.findElements(locator).isEmpty();
    }
}
