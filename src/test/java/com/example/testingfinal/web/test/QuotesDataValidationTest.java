package com.example.testingfinal.web.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class QuotesDataValidationTest extends BaseWebTest {

    @Test
    public void simplifiedQuoteTest() {
        System.out.println("[INFO] Starting simplified quote test");

        driver.get("https://smart-lab.ru/mobile/");

        // Пауза для загрузки
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        // Простая проверка: ищем элементы с классами из вашего HTML
        List<WebElement> quoteRows = driver.findElements(By.cssSelector(".quote-leaders__row"));
        System.out.println("Found .quote-leaders__row: " + quoteRows.size());

        if (quoteRows.isEmpty()) {
            // Альтернативный поиск
            quoteRows = driver.findElements(By.xpath("//div[contains(@class, 'row') and .//*[contains(text(), '%')]]"));
            System.out.println("Found alternative rows with %: " + quoteRows.size());
        }

        // Выводим информацию о первых нескольких котировках
        for (int i = 0; i < Math.min(5, quoteRows.size()); i++) {
            WebElement row = quoteRows.get(i);
            System.out.println("Row " + (i + 1) + ": " + row.getText());

            // Попробуем найти элементы внутри
            List<WebElement> spans = row.findElements(By.cssSelector("div"));
            System.out.println("  Contains " + spans.size() + " div elements");
            for (WebElement span : spans) {
                System.out.println("  - Div text: '" + span.getText() + "'");
            }
        }

        Assert.assertTrue(quoteRows.size() > 5, "Should find more than 5 quote rows");
    }
}
