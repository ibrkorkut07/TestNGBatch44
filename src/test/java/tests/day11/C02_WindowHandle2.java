package tests.day11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

/*
Tests package’inda yeni bir class olusturun: WindowHandle2
● go to https://the-internet.herokuapp.com/windows
● Verify that the text on the page is “Opening a new window”
● Verify that the page title is “The Internet”
● Click on "Click Here" button
● Verify that the title of the new page is “New Window”
● Verify that the text on the page is “New Window”
● Having returning to the previous page, verify that the page title is “The Internet”
 */
public class C02_WindowHandle2 {
    WebDriver driver;
    @BeforeClass
    public void setup () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @Test
    public void Test1 () {
        //  go to https://the-internet.herokuapp.com/windows
        driver.get("https://the-internet.herokuapp.com/windows");
        // Verify that the text on the page is “Opening a new window”
        String text1 = driver.findElement(By.xpath("//h3")).getText();
        String expectedText1 = "Opening a new window";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(text1.contains(expectedText1));
        // Verify that the page title is “The Internet”
        String pageTitle1 = driver.getTitle();
        String expectedPageTitle1 = "The Internet";
        softAssert.assertTrue(pageTitle1.contains(expectedPageTitle1));
        // Click on "Click Here" button
        driver.findElement(By.xpath("(//a[@target='_blank'])[1]")).click();
        // Verify that the title of the new page is “New Window”
        driver.get("https://the-internet.herokuapp.com/windows/new");
        String windowHandle = driver.getWindowHandle();

        String pageTitle2 = driver.getTitle();
        String expectedPageTitle2 = "New Window";
        softAssert.assertTrue(pageTitle2.contains(expectedPageTitle2));
        // Verify that the text on the page is “New Window”
        String text2 = driver.findElement(By.xpath("//h3")).getText();
        String expectedText2 = "New Window";
        softAssert.assertTrue(text2.contains(expectedText2));
        // Having returning to the previous page, verify that the page title is “The Internet”
        driver.switchTo().window(windowHandle);
        String previousTitle = driver.getTitle();
        String expectedPreviousTitle = "The Internet";
        softAssert.assertTrue(previousTitle.contains(expectedPreviousTitle));
        softAssert.assertAll();
    }
    @AfterClass
    public void tearDown () {
        driver.quit();
    }
}
