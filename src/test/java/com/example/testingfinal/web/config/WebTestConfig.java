package com.example.testingfinal.web.config;

import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public final class WebTestConfig {

    /**
     * Хранилище параметров, загружаемых из web-test.properties
     */
    private static final Properties properties = new Properties();

    /**
     * Статический инициализатор.
     * Выполняется один раз при загрузке класса.
     */
    static {
        try (InputStream input = WebTestConfig.class
                .getClassLoader()
                .getResourceAsStream("web-test.properties")) {

            if (input == null) {
                throw new RuntimeException("Файл web-test.properties не найден");
            }

            properties.load(input);
            System.out.println("[INFO] web-test.properties успешно загружен");

        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки web-test.properties", e);
        }
    }

    /**
     * Приватный конструктор.
     * Запрещает создание экземпляров класса.
     */
    private WebTestConfig() {
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    /**
     * Браузер для запуска тестов.
     *
     * Поддерживаемые значения:
     *  - chrome
     *  - firefox
     *  - edge
     *
     * По умолчанию: chrome
     *
     * @return имя браузера
     */
    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }

    /**
     * Таймаут ожиданий в секундах.
     *
     * Используется для:
     *  - WebDriverWait
     *  - ожиданий элементов
     *
     * @return таймаут в секундах
     */
    public static int getTimeoutSeconds() {
        return Integer.parseInt(
                properties.getProperty("timeout.seconds", "10")
        );
    }

    /**
     * Таймаут ожиданий в формате Duration.
     *
     * Удобен для передачи в WebDriverWait.
     *
     * @return таймаут в формате Duration
     */
    public static Duration getTimeout() {
        return Duration.ofSeconds(getTimeoutSeconds());
    }

}
