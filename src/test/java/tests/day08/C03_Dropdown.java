package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/*
Go to https://the-internet.herokuapp.com/dropdown
Select Option 1 by using "Index" and write it
Select Option 2 by using "value" and write it
Select Option 1 by using "VisibleText" and write it
Write all dropdown values
Find the size of Dropdown. Verify if there are 4 options in Dropdown.
*/
public class C03_Dropdown {
    WebDriver driver;

    @BeforeMethod
    public void setup () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(11));
        //  Go to https://the internet.herokuapp.com/dropdown
        driver.get("https://the-internet.herokuapp.com/dropdown");
    }
    @Test
    public void dropdownTest () {
        WebElement dropdownList = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropdownList);
        //  Select Option 1 by using "Index" and write it
        select.selectByIndex(1);
        System.out.println(select.getFirstSelectedOption().getText());
        //  Select Option 2 by using "value" and write it
        select.selectByValue("2");
        System.out.println(select.getFirstSelectedOption().getText());
        //  Select Option 1 by using "VisibleText" and write it
        select.selectByVisibleText("Option 1");
        System.out.println(select.getFirstSelectedOption().getText());
        //  Write all dropdown values
        List<WebElement> allOptions = select.getOptions();
        for (WebElement all : allOptions) {
            System.out.println(all.getText());
        }
        //  Find the size of Dropdown. Verify if there are 4 options in Dropdown.
        int actualSize = allOptions.size();
        int expectedSize = 4;
        Assert.assertEquals(actualSize, expectedSize, "The number of options does not meet the expectation");
    }
    @AfterMethod
    public void tearDown () {
        driver.close();
    }
}