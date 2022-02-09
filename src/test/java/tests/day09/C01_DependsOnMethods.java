package tests.day09;

/*
Go to https://www.amazon.com
1. Test : Test you went to amazon main page
2. Test : If the 1st test is successful search for “Nutella” by using search box and test that
          your search has been carried out
3. Test : If the search has been carried out for “Nutella”, click on the 1st product and
 verify that its price is $16.83
 */

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class C01_DependsOnMethods {
    WebDriver driver;

    @BeforeClass
    public void setup () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(11));
        driver.manage().window().maximize();
    }
    @Test
    public void logoTest () {
        driver.get("https://www.amazon.com/");
        String actualPage = driver.getCurrentUrl().toString();
        String expectedPage = "https://www.amazon.com/";
        Assert.assertEquals(actualPage, expectedPage);
/* or
        WebElement logoElement = driver.findElement(By.id("nav-logo-sprites"));
        Assert.assertTrue(logoElement.isDisplayed());
 */
    }
    @Test (dependsOnMethods = "logoTest")
    public void searchTest () {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Nutella" + Keys.ENTER);
        String actualTitle = driver.getTitle();
        String expectedTitle = "Nutella";
        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }
    @Test (dependsOnMethods = "searchTest")
    public void priceTest () {
        driver.findElement(By.xpath("(//img[@class='s-image'])[1]")).click();
        WebElement priceElement = driver.findElement(By.xpath("(//div[@class='a-section a-spacing-small a-spacing-top-small'])[1]"));
        String productPrice = priceElement.getText();
        String expectedPrice = "$14.99";
        Assert.assertTrue(productPrice.contains(expectedPrice));

        /*
        String actualPrice = driver.findElement(By.xpath("//span[@class='a-size-base a-color-price']")).getText();
        String expectedPrice = "$14.99";
        Assert.assertEquals(actualPrice, expectedPrice);
         */
    }
    @AfterClass
    public void tearDown () {
        driver.close();
    }
}
