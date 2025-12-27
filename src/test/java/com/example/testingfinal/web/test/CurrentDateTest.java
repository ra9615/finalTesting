package com.example.testingfinal.web.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CurrentDateTest extends BaseWebTest {
    @Test
    public void shouldDisplayCurrentOrRecentDateOnSmartLabMobile() throws InterruptedException {

        System.out.println("[INFO] Starting test: CurrentDateTest");

        driver.get(baseUrl);
        Thread.sleep(3000);

        // Поиск даты на странице
        String dateText = "Дата не найдена";
        java.util.List<org.openqa.selenium.WebElement> allElements = driver.findElements(
                org.openqa.selenium.By.xpath("//*")
        );

        for (org.openqa.selenium.WebElement el : allElements) {
            String text = el.getText();
            if (text.matches(".*\\d{2}\\.\\d{2}\\.\\d{4}.*")) {
                java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}").matcher(text);
                if (matcher.find()) {
                    dateText = matcher.group();
                    break;
                }
            }
        }

        System.out.println("[INFO] Date found: " + dateText);
        Assert.assertTrue(dateText.matches("\\d{2}\\.\\d{2}\\.\\d{4}"),
                "Дата должна быть в формате ДД.ММ.ГГГГ. Найдено: " + dateText);

        // Проверка даты без ChronoUnit
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate pageDate = LocalDate.parse(dateText, formatter);
            LocalDate currentDate = LocalDate.now();

            // Вычисляем разницу в днях без ChronoUnit
            long daysDifference = java.time.Duration.between(
                    pageDate.atStartOfDay(),
                    currentDate.atStartOfDay()
            ).toDays();

            // Или альтернативный способ:
            // long daysDifference = currentDate.toEpochDay() - pageDate.toEpochDay();

            System.out.println("[INFO] Days difference: " + daysDifference);

            Assert.assertTrue(daysDifference >= 0, "Дата из будущего: " + daysDifference + " дней");
            Assert.assertTrue(daysDifference <= 30, "Дата слишком старая: " + daysDifference + " дней");

        } catch (Exception e) {
            Assert.fail("Ошибка проверки даты: " + e.getMessage());
        }
    }
}
