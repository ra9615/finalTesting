package com.example.testingfinal.mobile.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    private final By SEARCH_FIELD = By.id("org.wikipedia.alpha:id/search_src_text");
    private final By SEARCH_RESULTS = By.id("org.wikipedia.alpha:id/page_list_item_title");

    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void searchFor(String query) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_FIELD))
                .sendKeys(query);
        System.out.println("[INFO] Выполнен поиск: " + query);
    }

    public List<WebElement> getSearchResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(SEARCH_RESULTS));
        List<WebElement> results = driver.findElements(SEARCH_RESULTS);
        System.out.println("[INFO] Найдено результатов: " + results.size());
        return results;
    }

    /**
     * Проверяет наличие результатов поиска.
     */
    public boolean hasSearchResults() {
        boolean hasResults = !getSearchResults().isEmpty();
        System.out.println("[INFO] Результаты поиска " + (hasResults ? "присутствуют" : "отсутствуют"));
        return hasResults;
    }

    public void openFirstSearchResult() {
        List<WebElement> results = getSearchResults();
        if (!results.isEmpty()) {
            results.get(0).click();
            System.out.println("[INFO] Открыт первый результат поиска");
        }
    }

}
