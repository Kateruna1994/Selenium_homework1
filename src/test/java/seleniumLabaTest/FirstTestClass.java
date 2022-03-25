package seleniumLabaTest;

import Utils.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
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

import java.time.Duration;

public class FirstTestClass {

    private WebDriver driver;

    @BeforeMethod
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");
    }

    @Test(invocationCount = 6)
    public void checkThatTheDataIsTheSame() {

        WebElement trackButton = driver.findElement
                (By.xpath("//div[@class='card-body']/h5[text()='Elements']"));
        Utils.scrollToElement(driver, trackButton);
        trackButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//span[text()='Text Box']")));

        WebElement clickButton = driver.findElement(By.xpath("//span[text()='Text Box']"));
        clickButton.click();


        WebElement clickEnterFullName = driver.findElement(By.id("userName"));
        clickEnterFullName.click();
        clickEnterFullName.sendKeys("Katerina");
        String expectedResultAfterEnterName = "Katerina";


        WebElement clickEnterEmail = driver.findElement(By.id("userEmail"));
        clickEnterEmail.click();
        clickEnterEmail.sendKeys("ketringoncharuk1994@fmail.com");
        String expectedResultAfterEnterEmail = "ketringoncharuk1994@fmail.com";

        WebElement clickOnTheFieldToEnterCurrentAddress = driver.findElement(By.id("currentAddress"));
        clickOnTheFieldToEnterCurrentAddress.click();
        clickOnTheFieldToEnterCurrentAddress.sendKeys("The city of Vinnytsia");
        String expectedResultAfterEnterCurrentAddress = "The city of Vinnytsia";

        Utils.scrollToElement(driver, clickOnTheFieldToEnterCurrentAddress);

        WebElement clickOnTheFieldToFillPermanentAddress = driver.findElement(By.id("permanentAddress"));
        clickOnTheFieldToFillPermanentAddress.click();
        clickOnTheFieldToFillPermanentAddress.sendKeys("Sverdlova");
        String expectedResultAfterEnterPermanentAddress = "Sverdlova";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));

        WebElement clickButtonSubmit = driver.findElement(By.id("submit"));
        clickButtonSubmit.click();


        WebElement outputNameLabel = driver.findElement(By.xpath("//p[text()='Katerina']"));
        String actualResultName = outputNameLabel.getText().replaceAll("Name:", "");

        Assertions.assertThat(actualResultName)
                .as("After entering the name must be" + expectedResultAfterEnterName)
                .isEqualTo(expectedResultAfterEnterName);

        WebElement outputEmailLabel = driver.findElement(By.id("email"));
        String actualResultEmail = outputEmailLabel.getText().replaceAll("Email:", "");

        Assertions.assertThat(actualResultEmail)
                .as("After entering the email must be" + expectedResultAfterEnterEmail)
                .isEqualTo(expectedResultAfterEnterEmail);

        WebElement outputCurrentAddressLabel = driver.findElement(By.xpath("//p[text()='Current Address :']"));
        String actualResultCurrentAddress = outputCurrentAddressLabel.getText()
                .replaceAll("Current Address :", "");

        Assertions.assertThat(actualResultCurrentAddress)
                .as("After entering the Current Address must be" + expectedResultAfterEnterCurrentAddress)
                .isEqualTo(expectedResultAfterEnterCurrentAddress);

        WebElement outputPermananetAddressLabel = driver.findElement(By.xpath("//p[text()='Permananet Address :']"));
        String actualResultPermananetAddress = outputPermananetAddressLabel.getText().replaceAll("Permananet Address :", "");

        Assertions.assertThat(actualResultPermananetAddress)
                .as("After entering the Permananet Address must be" + expectedResultAfterEnterPermanentAddress)
                .isEqualTo(expectedResultAfterEnterPermanentAddress);


    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}
