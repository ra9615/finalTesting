package com.example.testingfinal.web.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SmartLabMobilePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // Основные секции заголовки

    @FindBy(xpath = "//div[contains(@class, 'categories-slider__slide')]//span[contains(text(), 'Лидеры роста и падения')]")
    private WebElement leadersSectionHeader;

    @FindBy(xpath = "//div[contains(@class, 'categories-slider__slide')]//span[contains(text(), 'Индексы')]")
    private WebElement indicesSectionHeader;

    @FindBy(xpath = "//div[contains(@class, 'categories-slider__slide')]//span[contains(text(), 'Фьючерсы')]")
    private WebElement futuresSectionHeader;

    @FindBy(xpath = "//div[contains(@class, 'categories-slider__slide')]//span[contains(text(), 'FOREX')]")
    private WebElement forexSectionHeader;

    @FindBy(xpath = "//div[contains(@class, 'categories-slider__slide')]//span[contains(text(), 'Криптовалюты')]")
    private WebElement cryptocurrenciesSectionHeader;

    @FindBy(xpath = "//div[contains(text(), 'РОССИЯ')]")
    private WebElement russiaSectionHeader;

    @FindBy(xpath = "//div[contains(text(), 'США')]")
    private WebElement usaSectionHeader;

    // Элементы котировок (общая структура)
    @FindBy(css = "div[style*='display: flex'] > div")
    private List<WebElement> quoteElements;

    @FindBy(css = "div[style*='display: flex']")
    private List<WebElement> quoteRows;

    // Элементы форума
    @FindBy(xpath = "//h3[contains(text(), 'Мнение') or contains(text(), 'Как начать') or contains(text(), 'Динамика')]")
    private List<WebElement> forumPostTitles;

    @FindBy(xpath = "//a[contains(text(), 'Читать далее')]")
    private List<WebElement> readMoreLinks;

    @FindBy(xpath = "//div[contains(@class, 'transfer-btn__big') and contains(text(), 'Старый дизайн')]")
    private WebElement oldDesignLink;

    @FindBy(css = ".transfer-btn__big")
    private List<WebElement> transferButtons;

    // Дата на странице
    @FindBy(xpath = "//div[contains(text(), '25.12.2025') or contains(text(), '26.12.2025')]")
    private WebElement currentDateElement;

    // Конструктор
    public SmartLabMobilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Основные методы

    /**
     * Открывает мобильную версию Smart-Lab
     */
    public void openPage(String url) {
        driver.get(url);
        waitForPageLoad();
    }

    /**
     * Получает заголовок страницы
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Получает текущий URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Проверяет отображение секции "Лидеры роста и падения"
     */
    public boolean isLeadersSectionDisplayed() {
        try {
            return leadersSectionHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверяет отображение секции "Индексы"
     */
    public boolean isIndicesSectionDisplayed() {
        try {
            return indicesSectionHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверяет отображение секции "FOREX"
     */
    public boolean isForexSectionDisplayed() {
        try {
            return forexSectionHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверяет отображение секции "Криптовалюты"
     */
    public boolean isCryptocurrenciesSectionDisplayed() {
        try {
            return cryptocurrenciesSectionHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверяет отображение текущей даты
     */
    public boolean isCurrentDateDisplayed() {
        try {
            return currentDateElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Получает количество строк с котировками
     */
    public int getQuoteRowsCount() {
        wait.until(ExpectedConditions.visibilityOfAllElements(quoteRows));
        return quoteRows.size();
    }

    /**
     * Проверяет, что котировки отображаются (минимум 10 строк)
     */
    public boolean areQuotesDisplayed() {
        return getQuoteRowsCount() >= 10;
    }

    /**
     * Получает количество заголовков форумных постов
     */
    public int getForumPostTitlesCount() {
        return forumPostTitles.size();
    }

    /**
     * Кликает на первую ссылку "Читать далее"
     */
    public void clickFirstReadMoreLink() {
        if (!readMoreLinks.isEmpty()) {
            readMoreLinks.get(0).click();
            waitForPageLoad();
        }
    }

    /**
     * Кликает на ссылку "Старый дизайн"
     */
    public void clickOldDesignLink() {
        oldDesignLink.click();
        waitForPageLoad();
    }

    /**
     * Проверяет наличие ссылки "Старый дизайн"
     */
    public boolean isOldDesignLinkDisplayed() {
        try {
            return oldDesignLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Проверяет формат изменения цен (должны содержать %)
     */
    public boolean areQuoteChangesFormattedCorrectly() {
        for (WebElement row : quoteRows) {
            String rowText = row.getText();
            // Проверяем, что строка содержит процентное изменение
            if (!rowText.contains("%") &&
                    !rowText.matches(".*[+-]\\d+[.,]\\d+%.*")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ожидает загрузки страницы
     */
    private void waitForPageLoad() {
        wait.until(webDriver ->
                ((org.openqa.selenium.JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete")
        );

        // Дополнительная пауза для динамического контента
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Получает текущую дату с страницы
     */
    public String getCurrentDate() {
        try {
            return currentDateElement.getText();
        } catch (Exception e) {
            return "Дата не найдена";
        }
    }
}
