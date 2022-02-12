package tests.day10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

/*
go to https://the-internet.herokuapp.com/javascript_alerts
● Bir metod olusturun: acceptAlert
  1. butona tıklayın, uyarıdaki OK butonuna tıklayın ve result mesajının
  “You successfully clicked an alert” oldugunu test edin
● Bir metod olusturun: dismissAlert
  2. butona tıklayın, uyarıdaki Cancel butonuna tıklayın ve result mesajının
  “successfully” icermedigini test edin
● Bir metod olusturun: sendKeysAlert
  3. butona tıklayın, uyarıdaki metin kutusuna isminizi yazin, OK butonuna
  tıklayın ve result mesajın da isminizin görüntülendiğini doğrulayın.
*/
public class C01_Alerts {

    WebDriver driver;
    @BeforeClass // BeforeMethod yapsaydik, her methoddan once tekrar tekrar ayni sayfayi acacakti
                //  ama burada her islemi ayni sayfa uzerinden yaptigimiz icin tekrar tekrar acmaya gerek yok
    public void setup () {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(11));
        //  go to https://the-internet.herokuapp.com/javascript_alerts
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }
    @Test
    public void acceptAlert () {
        //  1. butona tıklayın, uyarıdaki OK butonuna tıklayın ve result mesajının
        //  “You successfully clicked an alert” oldugunu test edin
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        driver.switchTo().alert().accept();
        String resultText1 = driver.findElement(By.xpath("//p[@id='result']")).getText();
        String expectedText = "You successfully clicked an alert";
        Assert.assertTrue(resultText1.contains(expectedText));
    }
    @Test
    public void dismissAlert () {
        //  2. butona tıklayın, uyarıdaki Cancel butonuna tıklayın ve result mesajının
        //  “successfully” icermedigini test edin
        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
        driver.switchTo().alert().dismiss();
        driver.findElement(By.xpath("//p[text()='You clicked: Cancel']"));
        String resultText2 = driver.findElement(By.xpath("//p[text()='You clicked: Cancel']")).getText(); //  or //p[@id='result']
        String expectedText2 = "successfully";
        Assert.assertFalse(resultText2.contains(expectedText2));
    }
    @Test
    public void sendKeysAlert () {
    //  3. butona tıklayın, uyarıdaki metin kutusuna isminizi yazin, OK butonuna
    //  tıklayın ve result mesajın da isminizin görüntülendiğini doğrulayın.
        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
        driver.switchTo().alert().sendKeys("Ibrahim");
        driver.switchTo().alert().accept();
        String resultText3 = driver.findElement(By.xpath("//p[@id='result']")).getText();
        String expectedText3 = "Ibrahim";
        Assert.assertTrue(resultText3.contains(expectedText3));
    }
    @AfterClass
    public void tearDown () {
        driver.close();
    }
}
