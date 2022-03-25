package seleniumLabaTest;

import Utils.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FiveTestClass {
    private WebDriver driver;

    @BeforeMethod
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");

    }

    @Test(invocationCount = 5)
    public void checkThatTextNotificationsAreOpenAndClosed() {
        String expectedResult = "You clicked a button";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement adArrow = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("close-fixedban")));
        adArrow.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("close-fixedban")));

        WebElement trackButton = driver.findElement
                (By.xpath("//div[@class='card-body']/h5[text()='Alerts, Frame & Windows']"));
        Utils.scrollToElement(driver, trackButton);
        trackButton.click();

        WebElement clickButton = driver.findElement(By.xpath("//li[@id='item-1']/span[text()='Alerts']"));
        Utils.scrollToElement(driver, clickButton);
        clickButton.click();


        WebElement clickButtonClickMe = driver.findElement
                (By.xpath("//div[@class='col']/button[@id='alertButton']"));
        clickButtonClickMe.click();

        Alert alert = driver.switchTo().alert();
        String actualAlertLabel = alert.getText();

        alert.accept();

        Assertions.assertThat(actualAlertLabel)
                .as("After pressing the button we check whether the notification with the text has opened" +
                        expectedResult)
                .isEqualTo(expectedResult);

        boolean isAlertStillPresent;
        try {
            driver.switchTo().alert();
            isAlertStillPresent = true;
        } catch (NoAlertPresentException e) {
            isAlertStillPresent = false;
        }

        Assertions.assertThat(isAlertStillPresent)
                .as("After pressing the button we check whether the alert is closed")
                .isEqualTo(false);

    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}
