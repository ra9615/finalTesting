package com.example.testingfinal.mobile.page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ArticlePage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By GAMES_POPUP_CLOSE = By.id("org.wikipedia.alpha:id/closeButton");
    private final By TOOLBAR_GOT_IT = By.id("org.wikipedia.alpha:id/positiveButton");
    private final By ARTICLE_TITLE = By.id("org.wikipedia.alpha:id/view_page_title_text");
    private final By ARTICLE_TOOLBAR = By.id("org.wikipedia.alpha:id/view_page_header");
    private final By NAVIGATE_UP = AppiumBy.accessibilityId("Navigate up");
    private final By ARTICLE_CONTENT = By.id("org.wikipedia.alpha:id/page_web_view");

    public ArticlePage(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void closePopups() {
        closePopupIfVisible(GAMES_POPUP_CLOSE, "Wikipedia Games");
        closePopupIfVisible(TOOLBAR_GOT_IT, "Customize Toolbar");
    }

    private void closePopupIfVisible(By locator, String popupName) {
        try {
            WebDriverWait quickWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            quickWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
            System.out.println("[INFO] Закрыто всплывающее окно: " + popupName);
        } catch (Exception e) {
            // Окно отсутствует - это нормально
        }
    }

    public boolean isArticleOpen() {
        closePopups();
        return checkArticleIndicators();
    }

    private boolean checkArticleIndicators() {
        List<By> indicators = Arrays.asList(ARTICLE_TITLE, ARTICLE_TOOLBAR, NAVIGATE_UP);

        for (By indicator : indicators) {
            if (isElementPresent(indicator)) {
                System.out.println("[INFO] Статья открыта: найден индикатор " + indicator);
                return true;
            }
        }

        System.out.println("[WARN] Не найдены признаки открытой статьи");
        return false;
    }

    public boolean isArticleAccessible() {
        List<By> accessibilityChecks = Arrays.asList(ARTICLE_CONTENT, NAVIGATE_UP, ARTICLE_TITLE);

        for (By check : accessibilityChecks) {
            if (isElementPresent(check)) {
                System.out.println("[INFO] Статья доступна: " + check);
                return true;
            }
        }

        return false;
    }

    public void scrollDown() {
        int centerX = driver.manage().window().getSize().width / 2;
        int startY = (int) (driver.manage().window().getSize().height * 0.7);
        int endY = (int) (driver.manage().window().getSize().height * 0.3);

        performScroll(centerX, startY, centerX, endY);
        System.out.println("[INFO] Статья прокручена");
    }

    private void performScroll(int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 0);

        scroll.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(600),
                PointerInput.Origin.viewport(), endX, endY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(scroll));
    }

    private boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }
}
