package seleniumLabaTest;

import Utils.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jshell.execution.Util;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;

public class SecondTestClass {
    private WebDriver driver;

    @BeforeMethod
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");

    }

    @Test(invocationCount = 10)
    public void checkIfAnInscriptionAppears() {
        String expectedResult = "You have selected :wordFile";
        WebElement trackButton = driver.findElement
                (By.xpath("//div[@class='card-body']/h5[text()='Elements']"));
        Utils.scrollToElement(driver, trackButton);
        trackButton.click();

        WebElement clickButtonCheckBox = driver.findElement(By.xpath("//span[text()='Check Box']"));
        Utils.scrollToElement(driver, clickButtonCheckBox);
        clickButtonCheckBox.click();

        WebElement uncollapseHomeButton = driver.findElement(By.xpath("//span[@class='rct-text']/button"));
        uncollapseHomeButton.click();

        WebElement uncollapseDownloadsButton = driver.findElement
                (By.xpath("//label[@for='tree-node-downloads']/preceding-sibling::button"));
        uncollapseDownloadsButton.click();

        WebElement findTheNameWordFile_doc = driver.findElement(By.xpath("//span[text()='Word File.doc']"));
        findTheNameWordFile_doc.getText();

        WebElement clickOnCheckBoxWordFile_doc = driver.findElement
                (By.xpath("//label[@for='tree-node-wordFile']/span[@class='rct-checkbox']"));
        clickOnCheckBoxWordFile_doc.click();

       WebElement checkIfTheInscriptionAppears = driver.findElement(By.id("result"));
       String actualResultCheckTheAppearanceOfTheInscription = checkIfTheInscriptionAppears.getText();

        Assertions.assertThat(actualResultCheckTheAppearanceOfTheInscription)
                .as("After implementation the inscription should appear" + expectedResult)
                .isEqualTo(actualResultCheckTheAppearanceOfTheInscription);

    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }


}
