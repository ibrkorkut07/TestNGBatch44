package tests.day08;

/*
Go to https://www.amazon.com/
Test 1: Test 1: Test whether the number of categories next to the search box is 45.
Test 2:
Choose books in the category menu
Write Java in the search box and search for it
Write the number found in result
Verify/Test that the result contains the word "java"
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class C04_DropDownAmazon {
    WebDriver driver;
    Select select;
    WebElement dropbox;
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.amazon.com");
    }
    @Test
    public void Test01(){
        //  Test 1: Test whether the number of categories next to the search box is 45.
        dropbox=driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        select =new Select(dropbox);
        List<WebElement>optionList=select.getOptions();
        int actualSize=optionList.size();
        int expectedSize=45;
        Assert.assertEquals(actualSize, expectedSize);
    }
    @Test
    public void Test02(){
        select =new Select(dropbox);
        //  Choose books in the category menu
        select.selectByVisibleText("Books");
        //  Write Java in the search box and search for it
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Java"+ Keys.ENTER);
        //  Write the number found in result
        WebElement Result=driver.findElement(By.xpath("(//span[@dir='auto'])[1]"));
        System.out.println("Result number found: " + Result.getText());
        //  Verify/Test that the result contains the word "java"
        String actualTitle=(driver.findElement(By.xpath("(//span[@dir='auto'])[3]"))).getText();
        actualTitle=actualTitle.replaceAll("\"","");
        String expectedTitle="Java";
        Assert.assertEquals(actualTitle,expectedTitle);
    }
    @AfterClass
    public void teardown(){
        driver.close();
    }
}
