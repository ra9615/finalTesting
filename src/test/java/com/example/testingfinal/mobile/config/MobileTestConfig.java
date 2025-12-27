package com.example.testingfinal.mobile.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MobileTestConfig {

    private static final Properties PROPERTIES = loadProperties();

    private static Properties loadProperties() {
        Properties props = new Properties();
        String configFile = "mobile-test.properties";

        try (InputStream input = MobileTestConfig.class.getClassLoader()
                .getResourceAsStream(configFile)) {

            if (input == null) {
                throw new IllegalStateException("Файл конфигурации не найден: " + configFile);
            }

            props.load(input);
            System.out.println("[INFO] Конфигурация загружена из " + configFile);

        } catch (IOException e) {
            throw new IllegalStateException("Ошибка загрузки конфигурации", e);
        }

        return props;
    }

    // Геттеры для параметров конфигурации
    public static String getAppiumServerUrl() {
        return PROPERTIES.getProperty("mobile.appium.server.url");
    }

    public static String getPlatformName() {
        return PROPERTIES.getProperty("mobile.platformName", "ANDROID");
    }

    public static String getPlatformVersion() {
        return PROPERTIES.getProperty("mobile.platformVersion");
    }

    public static String getDeviceName() {
        return PROPERTIES.getProperty("mobile.deviceName");
    }

    public static String getAutomationName() {
        return PROPERTIES.getProperty("mobile.automationName", "UiAutomator2");
    }

    public static String getAppPackage() {
        return PROPERTIES.getProperty("mobile.appPackage");
    }

    public static String getAppActivity() {
        return PROPERTIES.getProperty("mobile.appActivity");
    }

    public static boolean isNoReset() {
        return Boolean.parseBoolean(PROPERTIES.getProperty("mobile.noReset", "true"));
    }

    public static boolean isAutoGrantPermissions() {
        return Boolean.parseBoolean(PROPERTIES.getProperty("mobile.autoGrantPermissions", "true"));
    }

    public static int getNewCommandTimeout() {
        return Integer.parseInt(PROPERTIES.getProperty("mobile.newCommandTimeout", "180"));
    }
}
