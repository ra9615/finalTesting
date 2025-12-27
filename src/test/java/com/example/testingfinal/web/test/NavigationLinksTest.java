package com.example.testingfinal.web.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class NavigationLinksTest extends BaseWebTest {
    @Test
    public void shouldNavigateViaLinksOnSmartLabMobile() throws InterruptedException {

        System.out.println("[INFO] Starting simplified navigation test");

        driver.get("https://smart-lab.ru/mobile/");
        Thread.sleep(4000);

        // 1. Поиск и клик на "Старый дизайн"
        List<WebElement> oldDesignElements = driver.findElements(
                By.xpath("//div[contains(@class, 'transfer-btn__big') and contains(text(), 'Старый дизайн')]")
        );

        if (!oldDesignElements.isEmpty()) {
            WebElement button = oldDesignElements.get(0);
            System.out.println("Found old design button: " + button.getText());

            // Простой JavaScript клик
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            Thread.sleep(3000);

            System.out.println("Current URL after click: " + driver.getCurrentUrl());
        }

        // 2. Возвращаемся и ищем "Читать далее"
        driver.navigate().back();
        Thread.sleep(3000);

        List<WebElement> readMoreLinks = driver.findElements(
                By.partialLinkText("Читать далее")
        );

        if (!readMoreLinks.isEmpty()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", readMoreLinks.get(0));
            Thread.sleep(3000);
            System.out.println("Navigated to post: " + driver.getCurrentUrl());
        }

        System.out.println("[INFO] Simplified navigation test completed");
    }
}
