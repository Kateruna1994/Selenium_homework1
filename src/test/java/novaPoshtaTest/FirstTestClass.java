package novaPoshtaTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTestClass {

    private WebDriver driver;

    @BeforeMethod
    public void setUpDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://novaposhta.ua/ru");
    }
    @Test(priority = 1)
    public void checkThatLableVidstezutuExsistOnMainPageTest(){
        String expectedResult = "Отследить";

        WebElement trackLabel = driver.findElement(By.xpath("//div[@class='search_cargo']/div[@class='header']"));
        String actualResult = trackLabel.getText();

        //Assert.assertEquals(actualResult, expectedResult);
        Assertions.assertThat(actualResult)
                .as("Expected text" + expectedResult + " not exist on the main page")
                .isEqualTo(expectedResult);


    }

    @Test(priority = 2)
    public void checkThatLiableShippingCostExistOnShippingPageTest(){
        String expectedText = "Стоимость доставки";
        WebElement costButton = driver.findElement(By.xpath("//a[@class='cost']/span"));
        costButton.click();
        WebElement pageTitleLabel = driver.findElement(By.xpath("//h1[@class='page_title']"));
        String actualPageTitleResult = pageTitleLabel.getText();

        Assertions.assertThat(actualPageTitleResult)
                .as("Page title should be" + expectedText)
                .isEqualTo(expectedText);

    }

    @Test(priority = 3)
    public void checkThatValidMarkAppearsAfterSelectCityFrom(){
        String expectedText = "Стоимость доставки";
        WebElement costButton = driver.findElement(By.xpath("//a[@class='cost']/span"));
        costButton.click();
        WebElement deliveryFromSelectCitySelectBox = driver.findElement(By.id("DeliveryForm_senderCity"));
        deliveryFromSelectCitySelectBox.click();
        WebElement fromCityOptions = driver.findElement
                (By.xpath("//ul[@id='delivery_sender_cities']//span[text()='Винница']"));
        fromCityOptions.click();
        WebElement deliveryFromRecipientCitySelectFrom = driver.findElement
                (By.id("DeliveryForm_recipientCity"));
        deliveryFromRecipientCitySelectFrom.click();
        WebElement toCityOption =  driver.findElement
                (By.xpath("//ul[@id='delivery_recipient_cities']//span[text()='Львов']"));
        toCityOption.click();

        WebElement routLabel = driver.findElement(By.xpath("//label[text()='Маршрут']"));
        String actualLabelClass = routLabel.getAttribute("class");


        Assertions.assertThat(actualLabelClass)
                .as("Class [valid] should exist in element, ig green check appears")
                .contains("valid");

    }

    @Test(priority = 4)
    public void checkThatElementsOnCoastPageHaveValidPropertiestTest(){

        WebElement costButton = driver.findElement(By.xpath("//a[@class='cost']/span"));
        costButton.click();
        WebElement packingCheckBox = driver.findElement(By.id("add-pack"));
        packingCheckBox.click();

        SoftAssertions softAssertions = new SoftAssertions();

        boolean isPackingCheckBoxSelected = packingCheckBox.isSelected();

        softAssertions.assertThat(isPackingCheckBoxSelected)
                .as("After click on packing checkbox it should be selected")
                .isTrue();
        WebElement amountImputeField = driver.findElement
                (By.xpath("//input[@class='DeliveryForm_packType_count']"));
        String isAmountImputeFieldReadOnly =  amountImputeField.getAttribute("readonly");
        softAssertions.assertThat(isAmountImputeFieldReadOnly)
                .as("Amount input should be read only by defoliate")
                .isEqualTo("true");

        String amountImputeFieldAttributeValue = amountImputeField.getAttribute("value");
        softAssertions.assertThat(amountImputeFieldAttributeValue)
                .as("Amount input  field value should be 1")
                .isEqualTo("1");


        softAssertions.assertAll();


    }

   @AfterMethod(alwaysRun = true)
    public void quitDriver(){
        driver.quit();

    }
}



