package com.example.testproject2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class BasicAuthenticationTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Silen\\A\\seleniumwd\\chromedriver_120_win64.exe");

        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBasicAuthentication() {
        String url = "https://the-internet.herokuapp.com/basic_auth";
        String username = "admin";
        String password = "admin";

        driver.get(url);

        handleAuthentication(username, password);

        WebElement element = null;
        try {
            element = driver.findElement(By.cssSelector("#content > .example > p"));
        } catch (Exception e) {
            Assertions.assertTrue(false, "Authentication failed. " + e);
        }

        Assertions.assertEquals(
                "Congratulations! You must have the proper credentials.",
                element.getText()
        );
    }

    private void handleAuthentication(String username, String password) {
        String authUrl = "https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";
        driver.get(authUrl);
    }
}
