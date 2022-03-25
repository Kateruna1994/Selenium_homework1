package seleniumLabaTest;

import Utils.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.StringTokenizer;

public class ThirdTestClass {
    private WebDriver driver;

    @BeforeMethod
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");
    }

    @Test
    public void checkDynamicClickAndDoubleClick() {
        String exceptionMessageAppearsAfterClickButtonClickMe = "You have done a dynamic click";
        String exceptionMessageAppearsAfterClickButtonRightClickMe = "You have done a right click";
        String exceptionMessageAppearsAfterClickButtonDoubleClickMe = "You have done a double click";

        WebElement trackButton = driver.findElement
                (By.xpath("//div[@class='card-body']/h5[text()='Elements']"));
        Utils.scrollToElement(driver, trackButton);
        trackButton.click();

        WebElement clickOnTheButton = driver.findElement(By.xpath("//span[text()='Buttons']"));
        Utils.scrollToElement(driver, clickOnTheButton);
        clickOnTheButton.click();

        WebElement clickOnClickMeButton = driver.findElement
                (By.xpath("//div[@class='mt-4']/button[text()='Click Me']"));
        clickOnClickMeButton.click();

        WebElement afterClickButtonClickMe = driver.findElement(By.id("dynamicClickMessage"));
        String actualResultAfterClickButtonClickMe = afterClickButtonClickMe.getText();

        Assertions.assertThat(actualResultAfterClickButtonClickMe)
                .as("After click button must be message appears" + exceptionMessageAppearsAfterClickButtonClickMe)
                .isEqualTo(exceptionMessageAppearsAfterClickButtonClickMe);

        WebElement clickOnButtonRightClickMe = driver.findElement
                (By.xpath("//div[@class='mt-4']/button[@id='rightClickBtn']"));
        Actions actions = new Actions(driver);
        actions.contextClick(clickOnButtonRightClickMe).build().perform();

        WebElement afterRightClickingOnTheButtonRightClickMe = driver.findElement
                (By.id("rightClickMessage"));
        String actualResultAfterClickOnTheButton = afterRightClickingOnTheButtonRightClickMe.getText();

        Assertions.assertThat(actualResultAfterClickOnTheButton)
                .as("After click button must be message appears" + exceptionMessageAppearsAfterClickButtonRightClickMe)
                .isEqualTo(exceptionMessageAppearsAfterClickButtonRightClickMe);

        WebElement doubleClickButtonDoubleClickMe = driver.findElement(By.id("doubleClickBtn"));
        actions.doubleClick(doubleClickButtonDoubleClickMe).build().perform();

        WebElement afterRightClickingOnTheButtonDoubleClickMe = driver.findElement
                (By.id("doubleClickMessage"));
        String actualResultAfterClickOnTheButtonDoubleClickMe =
                afterRightClickingOnTheButtonDoubleClickMe.getText();

        Assertions.assertThat(actualResultAfterClickOnTheButtonDoubleClickMe)
                .as("After click button must be message appears" +
                        exceptionMessageAppearsAfterClickButtonDoubleClickMe)
                .isEqualTo(exceptionMessageAppearsAfterClickButtonDoubleClickMe);
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}
