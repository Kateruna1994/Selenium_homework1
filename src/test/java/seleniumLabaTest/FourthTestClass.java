package seleniumLabaTest;

import Utils.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.UnableToSetCookieException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FourthTestClass {
    private WebDriver driver;

    @BeforeMethod
    public void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");
    }

    @Test
    public void checkIfNewTabIsOpen() {
        WebElement trackButton = driver.findElement
                (By.xpath("//div[@class='card-body']/h5[text()='Alerts, Frame & Windows']"));
        Utils.scrollToElement(driver, trackButton);
        trackButton.click();

        WebElement clickButtonBrowserWindows = driver.findElement
                (By.xpath("//li[@id='item-0']/span[text()='Browser Windows']"));
        Utils.scrollToElement(driver, clickButtonBrowserWindows);
        clickButtonBrowserWindows.click();

        WebElement clickButtonNewTab = driver.findElement(By.id("tabButton"));
        clickButtonNewTab.click();


        driver.switchTo().window("CDwindow-5020422F5DF27F6EA62A7706765098F6");

        WebElement checkWhetherTheTextIsCorrect = driver.findElement
                (By.xpath("//h1[text()='This is a sample page']"));
        String actualResultAfterGoingToNewPageWeSeeText = checkWhetherTheTextIsCorrect.getText();


    }
}
