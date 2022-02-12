package tests.day09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

/*
1. Go to “http://zero.webappsecurity.com/”
2. Click on Sign in button
3. Write “username” in login box
4. Write “password” in password box
5. Click on sign in button
6. Go to Pay Bills page in Online banking menu
7. Click on “Purchase Foreign Currency”
8. Choose Eurozone in “Currency” dropdown menu
9. Verify "Eurozone (Euro)" is selected by using softassert
10. Verify that dropdown list has the following options (use softassert)
"Select One", "Australia (dollar)", "Canada (dollar)", "Switzerland (franc)", "China
(yuan)", "Denmark (krone)", "Eurozone (euro)", "Great Britain (pound)", "Hong Kong
(dollar)", "Japan (yen)", "Mexico (peso)", "Norway (krone)", "New Zealand
(dollar)", "Sweden (krona)", "Singapore (dollar)", "Thailand (baht)
*/
public class C02_SoftAssertion {

    WebDriver driver;

    @BeforeMethod
    public void setup () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @Test
    public void test01 () {
        //  1. Go to “http://zero.webappsecurity.com/”
        driver.get("http://zero.webappsecurity.com/");
        //  2. Click on Sign in button
        driver.findElement(By.xpath("(//button[@id='signin_button'])[1]")).click();
        //  3. Write “username” in login box
        driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys("username");
        //  4. Write “password” in password box
        driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("password");
        //  5. Click on sign in button
        driver.findElement(By.xpath("//input[@value='Sign in']")).click();
        //  6. Go to Pay Bills page in Online banking menu
        driver.navigate().back(); // passes "not secure" warning
        driver.findElement(By.xpath("//a[@class='btn btn-small btn-info']")).click();
        driver.findElement(By.xpath("(//span[@id='pay_bills_link'])[1]")).click();
        //  7. Click on “Purchase Foreign Currency”
        driver.findElement(By.xpath("//a[text()='Purchase Foreign Currency']")).click();
        //  8. Choose Eurozone in “Currency” dropdown menu
        WebElement eurozone = driver.findElement(By.xpath("//option[@value='EUR']"));
        eurozone.click();
        //  9. Verify "Eurozone (Euro)" is selected by using softassert
        SoftAssert softAssertEuro = new SoftAssert();
        softAssertEuro.assertTrue(eurozone.isDisplayed());
        //  10. Verify that dropdown list has the following options (without softassert)
        List<WebElement> options = driver.findElements(By.xpath("//select[@id='pc_currency']"));
        // String allOptions = options.toString();
        for (WebElement all: options) {
            System.out.println(all.getText());
        }
        //  "Select One", "Australia (dollar)", "Canada (dollar)", "Switzerland (franc)", "China
        //  (yuan)", "Denmark (krone)", "Eurozone (euro)", "Great Britain (pound)", "Hong Kong
        //  (dollar)", "Japan (yen)", "Mexico (peso)", "Norway (krone)", "New Zealand
        //  (dollar)", "Sweden (krona)", "Singapore (dollar)", "Thailand (baht)
    }
    @AfterMethod
    public void tearDown () {
        driver.close();
    }
}
